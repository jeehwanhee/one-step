package com.jeepark.onestep.domain.service

import com.jeepark.onestep.domain.ml.Model_A
import com.jeepark.onestep.domain.model.Gender
import com.jeepark.onestep.domain.model.InitQuestions

// 고립도 산출 (순수 함수, Firebase/Android 의존 없음).
// 설문 답변과 프로필로 모델(Model_A) 입력 벡터를 만들고, 예측값을 0~100 점수로 바꾼다.
//
// 입력 범위 주의 — 화면에서 받을 수 있는 값과 모델이 학습한 값의 범위가 서로 다르다.
//
//   문항          화면 입력(InitQuestionScreen)   모델 범위(아래에서 잘라냄)
//   나이          가입 시 입력                     19~38
//   식사 횟수     0~10                             0~4
//   수면 시간     0~24                             2~20
//   샤워 횟수     0~7                              0~7
//   외출 일수     0~7                              0~4단계로 변환
//   미취업 기간   0~600개월                        0~240개월
//   활동 시간대   0~3                              0~3
//
// 화면은 "무엇을 입력할 수 있는가"를, 이 파일은 "모델이 모르는 범위의 값을 어떻게 다루는가"를 정한다.
// 범위를 벗어난 값은 경계값으로 잘라 외삽을 막는다(예: 식사 10회 → 4회로 계산).
// 둘은 별개 관심사라 일부러 맞추지 않았고, 화면 입력 범위를 바꿀 때는 모델 범위를 함께 확인해야 한다.

private val AGE_RANGE = 19.0..38.0
private val SHOWER_PER_WEEK_RANGE = 0.0..7.0
private val ACTIVE_TIME_RANGE = 0.0..3.0
private val HIKI_MONTHS_RANGE = 0.0..240.0
private val SLEEP_HOURS_RANGE = 2.0..20.0
private val MEALS_PER_DAY_RANGE = 0.0..4.0

private const val SCORE_SCALE = 100

/**
 * 주간 외출 일수(0~7)를 모델이 학습한 out_freq 단계(0~4)로 바꾼다.
 * 0회=0, 1~2회=1, 3~4회=2, 5~6회=3, 매일=4. 범위 밖 값은 0단계로 본다(화면이 0~7만 받아 방어용).
 */
fun scaleOutFreq(outsideDaysPerWeek: Int): Double = when (outsideDaysPerWeek) {
    0 -> 0.0
    1, 2 -> 1.0
    3, 4 -> 2.0
    5, 6 -> 3.0
    7 -> 4.0
    else -> 0.0
}

/**
 * Model_A 입력 벡터. 순서는 모델이 학습한 특성 순서로 바꾸면 안 된다:
 * [나이, 성별(남 0 / 여 1), 샤워, 외출 단계, 활동 시간대, 미취업 개월, 수면 시간, 식사 횟수]
 */
fun isolationFeatures(age: Int, gender: Gender, answers: InitQuestions): DoubleArray = doubleArrayOf(
    age.toDouble().coerceIn(AGE_RANGE),
    when (gender) {
        Gender.MALE -> 0.0
        Gender.FEMALE -> 1.0
    },
    answers.shower.toDouble().coerceIn(SHOWER_PER_WEEK_RANGE),
    scaleOutFreq(answers.outside),
    answers.activeTime.toDouble().coerceIn(ACTIVE_TIME_RANGE),
    answers.hiki.toDouble().coerceIn(HIKI_MONTHS_RANGE),
    answers.sleepTime.toDouble().coerceIn(SLEEP_HOURS_RANGE),
    answers.meal.toDouble().coerceIn(MEALS_PER_DAY_RANGE),
)

/**
 * 고립도 점수(0~100). 모델 예측값(0~1)에 100을 곱해 소수점을 **버린다**(반올림 아님).
 * 이미 저장된 점수 이력과 같은 방식이어야 해서 절삭을 유지한다.
 * [predict]는 테스트에서 모델을 대신할 수 있도록 주입할 수 있다.
 */
fun isolationScore(
    age: Int,
    gender: Gender,
    answers: InitQuestions,
    predict: (DoubleArray) -> Double = Model_A::predict,
): Int = (predict(isolationFeatures(age, gender, answers)) * SCORE_SCALE).toInt()
