package com.jeepark.onestep.util;

public class Model_B {

    public static double predict(double[] input) {
        double rawScore = score(input);

        return Math.max(1.0, Math.min(5.0, rawScore));
    }


    public static double score(double[] input) {
        double var0;
        if (input[0] < 90.0) {
            if (input[1] < 5.0) {
                if (input[3] < 2.93) {
                    if (input[3] < 2.71) {
                        if (input[3] < 2.61) {
                            var0 = -0.00016458909;
                        } else {
                            var0 = 0.00086940295;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var0 = -0.0016779605;
                        } else {
                            var0 = -0.00062346016;
                        }
                    }
                } else {
                    if (input[3] < 3.03) {
                        if (input[0] < 86.0) {
                            var0 = 0.0010754529;
                        } else {
                            var0 = 0.004616086;
                        }
                    } else {
                        if (input[3] < 4.66) {
                            var0 = 0.00000090174393;
                        } else {
                            var0 = 0.00053507474;
                        }
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[2] < 10.0) {
                        if (input[4] < 3.0) {
                            var0 = -0.00012843525;
                        } else {
                            var0 = 0.00057212805;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var0 = -0.00042852663;
                        } else {
                            var0 = -0.0020825497;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var0 = -0.00037447095;
                        } else {
                            var0 = 0.0028252793;
                        }
                    } else {
                        if (input[3] < 1.97) {
                            var0 = 0.00022026831;
                        } else {
                            var0 = 0.0019962285;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 3.83) {
                if (input[3] < 3.27) {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.18) {
                            var0 = -0.0009073752;
                        } else {
                            var0 = -0.00007624368;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var0 = 0.0016136082;
                        } else {
                            var0 = -0.0005487199;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[0] < 99.0) {
                            var0 = -0.00026351004;
                        } else {
                            var0 = 0.002339542;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var0 = -0.00047878324;
                        } else {
                            var0 = -0.003529535;
                        }
                    }
                }
            } else {
                if (input[2] < 5.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var0 = -0.00008382678;
                        } else {
                            var0 = 0.00067356735;
                        }
                    } else {
                        if (input[0] < 93.0) {
                            var0 = 0.0000044584276;
                        } else {
                            var0 = -0.0026903644;
                        }
                    }
                } else {
                    if (input[0] < 99.0) {
                        if (input[0] < 97.0) {
                            var0 = 0.0002423913;
                        } else {
                            var0 = 0.0028223;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var0 = 0.0002971659;
                        } else {
                            var0 = -0.0017798642;
                        }
                    }
                }
            }
        }
        double var1;
        if (input[0] < 19.0) {
            if (input[1] < 3.0) {
                if (input[4] < 7.0) {
                    if (input[2] < 9.0) {
                        if (input[0] < 18.0) {
                            var1 = 0.00017400685;
                        } else {
                            var1 = 0.0015112236;
                        }
                    } else {
                        if (input[0] < 7.0) {
                            var1 = -0.00008910696;
                        } else {
                            var1 = -0.0009976866;
                        }
                    }
                } else {
                    if (input[2] < 3.0) {
                        if (input[0] < 12.0) {
                            var1 = 0.0014769359;
                        } else {
                            var1 = -0.0005813643;
                        }
                    } else {
                        if (input[3] < 3.36) {
                            var1 = -0.00079223275;
                        } else {
                            var1 = -0.003078195;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[3] < 3.97) {
                        if (input[4] < 4.0) {
                            var1 = -0.000059169695;
                        } else {
                            var1 = 0.0014542992;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var1 = -0.00018897721;
                        } else {
                            var1 = -0.0022786146;
                        }
                    }
                } else {
                    if (input[3] < 4.38) {
                        if (input[2] < 9.0) {
                            var1 = 0.00024895085;
                        } else {
                            var1 = 0.003434584;
                        }
                    } else {
                        if (input[0] < 5.0) {
                            var1 = 0.0024144202;
                        } else {
                            var1 = 0.0054451516;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.49) {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.77) {
                            var1 = 0.00015684287;
                        } else {
                            var1 = 0.0013016773;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var1 = 0.000018398567;
                        } else {
                            var1 = -0.0011775832;
                        }
                    }
                } else {
                    if (input[3] < 1.26) {
                        if (input[1] < 2.0) {
                            var1 = 0.0009575069;
                        } else {
                            var1 = -0.0014154072;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var1 = -0.0006161843;
                        } else {
                            var1 = 0.0002175106;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[2] < 6.0) {
                        if (input[4] < 4.0) {
                            var1 = -0.00011315433;
                        } else {
                            var1 = 0.0015067555;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var1 = 0.00018074685;
                        } else {
                            var1 = -0.00090012525;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[2] < 4.0) {
                            var1 = 0.0010481045;
                        } else {
                            var1 = -0.0022307166;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var1 = -0.00078677834;
                        } else {
                            var1 = 0.0035310544;
                        }
                    }
                }
            }
        }
        double var2;
        if (input[0] < 75.0) {
            if (input[4] < 1.0) {
                if (input[3] < 1.45) {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.19) {
                            var2 = -0.0006344279;
                        } else {
                            var2 = 0.0007995776;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var2 = 0.00001560698;
                        } else {
                            var2 = 0.002100678;
                        }
                    }
                } else {
                    if (input[3] < 2.22) {
                        if (input[1] < 4.0) {
                            var2 = 0.000119486605;
                        } else {
                            var2 = -0.0034958844;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var2 = 0.00036442667;
                        } else {
                            var2 = -0.00040000267;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 4.98) {
                        if (input[2] < 4.0) {
                            var2 = 0.00015523046;
                        } else {
                            var2 = -0.00060249463;
                        }
                    } else {
                        var2 = 0.0038327544;
                    }
                } else {
                    if (input[3] < 1.62) {
                        if (input[3] < 1.16) {
                            var2 = -0.001057218;
                        } else {
                            var2 = -0.00023507574;
                        }
                    } else {
                        if (input[3] < 1.94) {
                            var2 = 0.0013547329;
                        } else {
                            var2 = 0.00028506052;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.11) {
                if (input[3] < 4.03) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.03) {
                            var2 = -0.00036419427;
                        } else {
                            var2 = 0.00062948343;
                        }
                    } else {
                        if (input[0] < 92.0) {
                            var2 = -0.00020902665;
                        } else {
                            var2 = -0.0008338491;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[0] < 81.0) {
                            var2 = 0.0008113682;
                        } else {
                            var2 = -0.0005189802;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var2 = -0.004591962;
                        } else {
                            var2 = -0.0002139926;
                        }
                    }
                }
            } else {
                if (input[3] < 4.14) {
                    if (input[2] < 7.0) {
                        if (input[2] < 5.0) {
                            var2 = -0.00020631553;
                        } else {
                            var2 = 0.0011971921;
                        }
                    } else {
                        var2 = 0.003990346;
                    }
                } else {
                    if (input[4] < 7.0) {
                        if (input[3] < 4.94) {
                            var2 = 0.00011007255;
                        } else {
                            var2 = 0.001972729;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var2 = -0.003795147;
                        } else {
                            var2 = 0.0025494322;
                        }
                    }
                }
            }
        }
        double var3;
        if (input[1] < 5.0) {
            if (input[2] < 9.0) {
                if (input[1] < 4.0) {
                    if (input[3] < 2.93) {
                        if (input[2] < 4.0) {
                            var3 = -0.0004696691;
                        } else {
                            var3 = -0.000013347705;
                        }
                    } else {
                        if (input[3] < 3.02) {
                            var3 = 0.0015175475;
                        } else {
                            var3 = 0.000062181665;
                        }
                    }
                } else {
                    if (input[3] < 4.65) {
                        if (input[2] < 4.0) {
                            var3 = -0.0001603118;
                        } else {
                            var3 = -0.00078470027;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var3 = -0.000027547923;
                        } else {
                            var3 = 0.0033257038;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.0) {
                        if (input[4] < 5.0) {
                            var3 = -0.00004281716;
                        } else {
                            var3 = -0.00078974525;
                        }
                    } else {
                        if (input[3] < 4.29) {
                            var3 = 0.0017792067;
                        } else {
                            var3 = 0.00015048223;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.74) {
                            var3 = 0.0023965097;
                        } else {
                            var3 = -0.0007640089;
                        }
                    } else {
                        if (input[0] < 25.0) {
                            var3 = 0.00614866;
                        } else {
                            var3 = 0.0038393636;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[3] < 3.88) {
                    if (input[3] < 3.58) {
                        if (input[2] < 10.0) {
                            var3 = 0.00015713017;
                        } else {
                            var3 = -0.0015882527;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var3 = 0.00047299304;
                        } else {
                            var3 = 0.0040966035;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 4.22) {
                            var3 = -0.0004149427;
                        } else {
                            var3 = 0.0002565809;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var3 = 0.00086112815;
                        } else {
                            var3 = -0.0029926712;
                        }
                    }
                }
            } else {
                if (input[3] < 4.06) {
                    if (input[0] < 43.0) {
                        if (input[2] < 4.0) {
                            var3 = -0.000058999885;
                        } else {
                            var3 = 0.0020873942;
                        }
                    } else {
                        if (input[0] < 88.0) {
                            var3 = 0.00001786479;
                        } else {
                            var3 = -0.0016398191;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var3 = -0.0008936829;
                        } else {
                            var3 = 0.002159357;
                        }
                    } else {
                        if (input[3] < 4.32) {
                            var3 = 0.002824896;
                        } else {
                            var3 = 0.0054285913;
                        }
                    }
                }
            }
        }
        double var4;
        if (input[0] < 75.0) {
            if (input[4] < 1.0) {
                if (input[3] < 1.45) {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.19) {
                            var4 = -0.0005948628;
                        } else {
                            var4 = 0.0007728219;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var4 = 0.000016346077;
                        } else {
                            var4 = 0.001993654;
                        }
                    }
                } else {
                    if (input[3] < 2.26) {
                        if (input[1] < 4.0) {
                            var4 = -0.00002604788;
                        } else {
                            var4 = -0.0033208728;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var4 = 0.00034399566;
                        } else {
                            var4 = -0.00035288383;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 4.94) {
                        if (input[2] < 4.0) {
                            var4 = 0.00013913117;
                        } else {
                            var4 = -0.0005900279;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var4 = -0.00067261065;
                        } else {
                            var4 = 0.0037150164;
                        }
                    }
                } else {
                    if (input[3] < 1.62) {
                        if (input[3] < 1.16) {
                            var4 = -0.0010019483;
                        } else {
                            var4 = -0.00021658854;
                        }
                    } else {
                        if (input[3] < 1.94) {
                            var4 = 0.0012951096;
                        } else {
                            var4 = 0.0002665889;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 3.8) {
                if (input[3] < 3.39) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.85) {
                            var4 = -0.0000335418;
                        } else {
                            var4 = 0.0014652571;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var4 = -0.00058377575;
                        } else {
                            var4 = -0.00001703253;
                        }
                    }
                } else {
                    if (input[4] < 3.0) {
                        if (input[2] < 7.0) {
                            var4 = 0.00025654002;
                        } else {
                            var4 = -0.00035517217;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var4 = -0.0004214677;
                        } else {
                            var4 = -0.00227482;
                        }
                    }
                }
            } else {
                if (input[2] < 5.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var4 = -0.000115192925;
                        } else {
                            var4 = 0.00067530543;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var4 = 0.0010096054;
                        } else {
                            var4 = -0.0027842736;
                        }
                    }
                } else {
                    if (input[3] < 3.83) {
                        var4 = 0.003978658;
                    } else {
                        if (input[4] < 5.0) {
                            var4 = 0.00005039763;
                        } else {
                            var4 = 0.0009244497;
                        }
                    }
                }
            }
        }
        double var5;
        if (input[0] < 25.0) {
            if (input[1] < 5.0) {
                if (input[0] < 1.0) {
                    if (input[3] < 1.77) {
                        if (input[3] < 1.67) {
                            var5 = 0.0001042525;
                        } else {
                            var5 = 0.0013718278;
                        }
                    } else {
                        if (input[2] < 1.0) {
                            var5 = 0.0015596927;
                        } else {
                            var5 = -0.0015641273;
                        }
                    }
                } else {
                    if (input[0] < 4.0) {
                        if (input[3] < 1.3) {
                            var5 = -0.0010706525;
                        } else {
                            var5 = 0.0012009776;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var5 = -0.00007351001;
                        } else {
                            var5 = 0.00052335975;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[3] < 3.8) {
                        if (input[3] < 3.48) {
                            var5 = 0.00021441118;
                        } else {
                            var5 = 0.004499247;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var5 = -0.00013210342;
                        } else {
                            var5 = -0.0020024972;
                        }
                    }
                } else {
                    if (input[3] < 4.41) {
                        if (input[2] < 4.0) {
                            var5 = 0.00010846019;
                        } else {
                            var5 = 0.0019975172;
                        }
                    } else {
                        var5 = 0.0056137484;
                    }
                }
            }
        } else {
            if (input[2] < 9.0) {
                if (input[3] < 4.86) {
                    if (input[0] < 100.0) {
                        if (input[4] < 6.0) {
                            var5 = -0.00008049839;
                        } else {
                            var5 = -0.00032587923;
                        }
                    } else {
                        if (input[3] < 1.74) {
                            var5 = 0.0003156852;
                        } else {
                            var5 = -0.0017440524;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 2.0) {
                            var5 = 0.0015668087;
                        } else {
                            var5 = -0.0001351954;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var5 = 0.0007597787;
                        } else {
                            var5 = 0.0037615472;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 3.38) {
                        if (input[0] < 52.0) {
                            var5 = 0.00013213113;
                        } else {
                            var5 = -0.0006733704;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var5 = -0.00052424276;
                        } else {
                            var5 = 0.0005977022;
                        }
                    }
                } else {
                    if (input[4] < 5.0) {
                        if (input[4] < 2.0) {
                            var5 = -0.001132328;
                        } else {
                            var5 = 0.00053915806;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var5 = 0.0031544957;
                        } else {
                            var5 = 0.0013990292;
                        }
                    }
                }
            }
        }
        double var6;
        if (input[0] < 90.0) {
            if (input[1] < 5.0) {
                if (input[3] < 2.93) {
                    if (input[3] < 2.71) {
                        if (input[3] < 2.61) {
                            var6 = -0.00013527737;
                        } else {
                            var6 = 0.00083609665;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var6 = -0.0015710216;
                        } else {
                            var6 = -0.0005954009;
                        }
                    }
                } else {
                    if (input[3] < 3.03) {
                        if (input[0] < 86.0) {
                            var6 = 0.0009825864;
                        } else {
                            var6 = 0.0042884112;
                        }
                    } else {
                        if (input[3] < 4.66) {
                            var6 = -0.000003121673;
                        } else {
                            var6 = 0.00045718718;
                        }
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[2] < 10.0) {
                        if (input[3] < 2.9) {
                            var6 = 0.0004952597;
                        } else {
                            var6 = -0.00015698373;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var6 = -0.00034708017;
                        } else {
                            var6 = -0.0019402038;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var6 = -0.00032984823;
                        } else {
                            var6 = 0.0025028896;
                        }
                    } else {
                        if (input[3] < 1.97) {
                            var6 = 0.00016891642;
                        } else {
                            var6 = 0.0017537529;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 3.83) {
                if (input[3] < 3.27) {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.18) {
                            var6 = -0.0007925361;
                        } else {
                            var6 = -0.00003268364;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var6 = 0.001581291;
                        } else {
                            var6 = -0.00042686448;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[0] < 99.0) {
                            var6 = -0.00019848408;
                        } else {
                            var6 = 0.0023115159;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var6 = -0.00043943973;
                        } else {
                            var6 = -0.0031749965;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var6 = -0.00004919538;
                        } else {
                            var6 = 0.0005532732;
                        }
                    } else {
                        if (input[0] < 93.0) {
                            var6 = -0.000060862305;
                        } else {
                            var6 = -0.0027247276;
                        }
                    }
                } else {
                    if (input[0] < 99.0) {
                        if (input[0] < 97.0) {
                            var6 = 0.00021172415;
                        } else {
                            var6 = 0.0025125782;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var6 = 0.00016169208;
                        } else {
                            var6 = -0.0017014444;
                        }
                    }
                }
            }
        }
        double var7;
        if (input[0] < 25.0) {
            if (input[1] < 2.0) {
                if (input[3] < 4.94) {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.85) {
                            var7 = -0.0005663403;
                        } else {
                            var7 = 0.00065326405;
                        }
                    } else {
                        if (input[3] < 3.02) {
                            var7 = -0.00019478123;
                        } else {
                            var7 = -0.0013648522;
                        }
                    }
                } else {
                    if (input[0] < 23.0) {
                        var7 = 0.002499661;
                    } else {
                        var7 = -0.00028540194;
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 2.68) {
                        if (input[2] < 4.0) {
                            var7 = -0.0005151874;
                        } else {
                            var7 = 0.001373574;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var7 = 0.0005430823;
                        } else {
                            var7 = -0.0008108564;
                        }
                    }
                } else {
                    if (input[3] < 3.37) {
                        if (input[3] < 1.67) {
                            var7 = -0.00059036276;
                        } else {
                            var7 = 0.0003859016;
                        }
                    } else {
                        if (input[0] < 2.0) {
                            var7 = -0.0020540494;
                        } else {
                            var7 = 0.0015680911;
                        }
                    }
                }
            }
        } else {
            if (input[2] < 9.0) {
                if (input[3] < 4.65) {
                    if (input[0] < 100.0) {
                        if (input[2] < 4.0) {
                            var7 = -0.00026077518;
                        } else {
                            var7 = -0.00006209532;
                        }
                    } else {
                        if (input[3] < 3.83) {
                            var7 = -0.0016255829;
                        } else {
                            var7 = 0.0003749378;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[2] < 4.0) {
                            var7 = -0.0003961712;
                        } else {
                            var7 = 0.00009302976;
                        }
                    } else {
                        if (input[0] < 31.0) {
                            var7 = -0.0020657897;
                        } else {
                            var7 = 0.00086750276;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[4] < 5.0) {
                        if (input[3] < 1.7) {
                            var7 = -0.0006344509;
                        } else {
                            var7 = 0.00027699294;
                        }
                    } else {
                        if (input[3] < 4.0) {
                            var7 = -0.0008939525;
                        } else {
                            var7 = 0.0010857218;
                        }
                    }
                } else {
                    if (input[4] < 5.0) {
                        if (input[4] < 2.0) {
                            var7 = -0.001071947;
                        } else {
                            var7 = 0.0005338634;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var7 = 0.0029952326;
                        } else {
                            var7 = 0.0012690821;
                        }
                    }
                }
            }
        }
        double var8;
        if (input[0] < 19.0) {
            if (input[1] < 2.0) {
                if (input[4] < 7.0) {
                    if (input[0] < 1.0) {
                        if (input[4] < 1.0) {
                            var8 = 0.000046369434;
                        } else {
                            var8 = -0.0019376808;
                        }
                    } else {
                        if (input[0] < 2.0) {
                            var8 = 0.0015813867;
                        } else {
                            var8 = -0.00003446713;
                        }
                    }
                } else {
                    if (input[3] < 3.84) {
                        if (input[2] < 3.0) {
                            var8 = 0.00077933224;
                        } else {
                            var8 = -0.0010424237;
                        }
                    } else {
                        var8 = -0.003978574;
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 2.88) {
                        if (input[4] < 1.0) {
                            var8 = -0.0011825063;
                        } else {
                            var8 = 0.0008623616;
                        }
                    } else {
                        if (input[3] < 3.08) {
                            var8 = -0.0026126963;
                        } else {
                            var8 = -0.00017495232;
                        }
                    }
                } else {
                    if (input[3] < 3.37) {
                        if (input[3] < 1.35) {
                            var8 = -0.00081122573;
                        } else {
                            var8 = 0.00044278984;
                        }
                    } else {
                        if (input[0] < 2.0) {
                            var8 = -0.001966015;
                        } else {
                            var8 = 0.0016745118;
                        }
                    }
                }
            }
        } else {
            if (input[2] < 9.0) {
                if (input[3] < 4.44) {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.99) {
                            var8 = -0.00022351625;
                        } else {
                            var8 = 0.00010735572;
                        }
                    } else {
                        if (input[3] < 2.63) {
                            var8 = 0.00010773425;
                        } else {
                            var8 = -0.00077088585;
                        }
                    }
                } else {
                    if (input[4] < 2.0) {
                        if (input[3] < 4.84) {
                            var8 = -0.00022819029;
                        } else {
                            var8 = -0.00043311506;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var8 = 0.00019621656;
                        } else {
                            var8 = 0.0010238163;
                        }
                    }
                }
            } else {
                if (input[4] < 3.0) {
                    if (input[0] < 90.0) {
                        if (input[0] < 86.0) {
                            var8 = -0.00018215255;
                        } else {
                            var8 = 0.0016442154;
                        }
                    } else {
                        if (input[0] < 96.0) {
                            var8 = -0.0028790317;
                        } else {
                            var8 = -0.0004652864;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.0) {
                            var8 = -0.00036069122;
                        } else {
                            var8 = 0.0009110492;
                        }
                    } else {
                        if (input[3] < 4.02) {
                            var8 = 0.0021725118;
                        } else {
                            var8 = -0.000057369325;
                        }
                    }
                }
            }
        }
        double var9;
        if (input[0] < 75.0) {
            if (input[4] < 1.0) {
                if (input[3] < 1.43) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var9 = 0.0006856386;
                        } else {
                            var9 = -0.0005924128;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var9 = 0.00029830376;
                        } else {
                            var9 = 0.0023151075;
                        }
                    }
                } else {
                    if (input[3] < 2.26) {
                        if (input[1] < 4.0) {
                            var9 = -0.0000013063784;
                        } else {
                            var9 = -0.0030212214;
                        }
                    } else {
                        if (input[0] < 53.0) {
                            var9 = -0.0003620563;
                        } else {
                            var9 = 0.0001993104;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[3] < 2.93) {
                        if (input[3] < 1.11) {
                            var9 = 0.0005217903;
                        } else {
                            var9 = -0.00039921707;
                        }
                    } else {
                        if (input[3] < 3.03) {
                            var9 = 0.0016314021;
                        } else {
                            var9 = 0.00011612663;
                        }
                    }
                } else {
                    if (input[3] < 1.16) {
                        if (input[2] < 4.0) {
                            var9 = -0.000012690402;
                        } else {
                            var9 = -0.0021663203;
                        }
                    } else {
                        if (input[3] < 2.71) {
                            var9 = 0.0006494849;
                        } else {
                            var9 = 0.000107768356;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[3] < 2.08) {
                    if (input[0] < 90.0) {
                        if (input[0] < 76.0) {
                            var9 = 0.002338197;
                        } else {
                            var9 = -0.000048782797;
                        }
                    } else {
                        if (input[3] < 1.16) {
                            var9 = 0.0014125347;
                        } else {
                            var9 = -0.0013876964;
                        }
                    }
                } else {
                    if (input[0] < 77.0) {
                        if (input[3] < 3.59) {
                            var9 = -0.0014399973;
                        } else {
                            var9 = 0.0003451936;
                        }
                    } else {
                        if (input[0] < 81.0) {
                            var9 = 0.0007632402;
                        } else {
                            var9 = 0.000086150096;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[0] < 86.0) {
                            var9 = 0.00062688295;
                        } else {
                            var9 = 0.0020228545;
                        }
                    } else {
                        if (input[3] < 2.69) {
                            var9 = 0.00014382011;
                        } else {
                            var9 = -0.0032374572;
                        }
                    }
                } else {
                    if (input[3] < 3.79) {
                        if (input[3] < 3.36) {
                            var9 = -0.00027544997;
                        } else {
                            var9 = -0.0016933847;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var9 = -0.00079406967;
                        } else {
                            var9 = 0.0009890718;
                        }
                    }
                }
            }
        }
        double var10;
        if (input[0] < 29.0) {
            if (input[1] < 2.0) {
                if (input[0] < 1.0) {
                    if (input[4] < 1.0) {
                        var10 = 0.00005426109;
                    } else {
                        if (input[4] < 6.0) {
                            var10 = -0.0018418849;
                        } else {
                            var10 = -0.00046173632;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[0] < 2.0) {
                            var10 = 0.0015023317;
                        } else {
                            var10 = 0.000087113185;
                        }
                    } else {
                        if (input[3] < 4.81) {
                            var10 = -0.0006380013;
                        } else {
                            var10 = 0.0023896396;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[3] < 1.88) {
                        if (input[3] < 1.49) {
                            var10 = 0.000043946897;
                        } else {
                            var10 = 0.0020774012;
                        }
                    } else {
                        if (input[3] < 2.08) {
                            var10 = -0.0038317037;
                        } else {
                            var10 = -0.0003625935;
                        }
                    }
                } else {
                    if (input[3] < 1.72) {
                        if (input[2] < 4.0) {
                            var10 = -0.000952372;
                        } else {
                            var10 = 0.000082005514;
                        }
                    } else {
                        if (input[3] < 1.94) {
                            var10 = 0.0023140656;
                        } else {
                            var10 = 0.00053634256;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.86) {
                if (input[2] < 9.0) {
                    if (input[4] < 6.0) {
                        if (input[3] < 3.08) {
                            var10 = -0.00022070506;
                        } else {
                            var10 = 0.00010642193;
                        }
                    } else {
                        if (input[3] < 2.71) {
                            var10 = 0.00011478438;
                        } else {
                            var10 = -0.0006886572;
                        }
                    }
                } else {
                    if (input[0] < 31.0) {
                        if (input[3] < 4.58) {
                            var10 = -0.0024527425;
                        } else {
                            var10 = -0.0002264774;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var10 = -0.00006345069;
                        } else {
                            var10 = 0.00070267014;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var10 = -0.00046423377;
                        } else {
                            var10 = 0.0037075311;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var10 = -0.0005922035;
                        } else {
                            var10 = 0.00037299708;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 3.0) {
                            var10 = 0.002430872;
                        } else {
                            var10 = -0.00052268506;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var10 = 0.00029646556;
                        } else {
                            var10 = 0.0036702752;
                        }
                    }
                }
            }
        }
        double var11;
        if (input[0] < 90.0) {
            if (input[1] < 5.0) {
                if (input[2] < 9.0) {
                    if (input[1] < 4.0) {
                        if (input[3] < 2.93) {
                            var11 = -0.00016907086;
                        } else {
                            var11 = 0.00018476219;
                        }
                    } else {
                        if (input[3] < 4.65) {
                            var11 = -0.0004383164;
                        } else {
                            var11 = 0.00077337405;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[4] < 5.0) {
                            var11 = 0.000082471954;
                        } else {
                            var11 = -0.00048147608;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var11 = -0.00026328166;
                        } else {
                            var11 = 0.0031569656;
                        }
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[2] < 10.0) {
                        if (input[3] < 2.9) {
                            var11 = 0.00047324147;
                        } else {
                            var11 = -0.00016054785;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var11 = -0.00030120512;
                        } else {
                            var11 = -0.0019659754;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var11 = -0.00033478832;
                        } else {
                            var11 = 0.002311635;
                        }
                    } else {
                        if (input[3] < 1.09) {
                            var11 = -0.0031987191;
                        } else {
                            var11 = 0.001314436;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 3.83) {
                if (input[3] < 3.27) {
                    if (input[4] < 6.0) {
                        if (input[3] < 1.07) {
                            var11 = 0.0015917832;
                        } else {
                            var11 = -0.00043502598;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var11 = 0.0014808303;
                        } else {
                            var11 = -0.00032871112;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[0] < 99.0) {
                            var11 = -0.00019440711;
                        } else {
                            var11 = 0.0022477866;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var11 = -0.00036769957;
                        } else {
                            var11 = -0.00285123;
                        }
                    }
                }
            } else {
                if (input[4] < 7.0) {
                    if (input[1] < 4.0) {
                        if (input[2] < 6.0) {
                            var11 = -0.000028513487;
                        } else {
                            var11 = 0.0013268896;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var11 = -0.000671261;
                        } else {
                            var11 = 0.0026355663;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[3] < 4.14) {
                            var11 = -0.0005182823;
                        } else {
                            var11 = -0.0033680182;
                        }
                    } else {
                        if (input[0] < 96.0) {
                            var11 = 0.000842762;
                        } else {
                            var11 = 0.0030563474;
                        }
                    }
                }
            }
        }
        double var12;
        if (input[0] < 43.0) {
            if (input[1] < 2.0) {
                if (input[3] < 4.38) {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.91) {
                            var12 = -0.00047018705;
                        } else {
                            var12 = 0.0008542821;
                        }
                    } else {
                        if (input[3] < 3.02) {
                            var12 = -0.00012569339;
                        } else {
                            var12 = -0.0017226421;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[2] < 4.0) {
                            var12 = -0.00067085965;
                        } else {
                            var12 = 0.00010943525;
                        }
                    } else {
                        if (input[0] < 39.0) {
                            var12 = 0.0010826091;
                        } else {
                            var12 = -0.0019469083;
                        }
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.99) {
                            var12 = -0.0010794774;
                        } else {
                            var12 = 0.000047176185;
                        }
                    } else {
                        if (input[3] < 2.74) {
                            var12 = 0.00043600655;
                        } else {
                            var12 = -0.00022483482;
                        }
                    }
                } else {
                    if (input[3] < 2.49) {
                        if (input[1] < 3.0) {
                            var12 = -0.00073319074;
                        } else {
                            var12 = 0.00027766748;
                        }
                    } else {
                        if (input[3] < 2.68) {
                            var12 = 0.002091226;
                        } else {
                            var12 = 0.0006229177;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.98) {
                if (input[2] < 9.0) {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.03) {
                            var12 = -0.00034554175;
                        } else {
                            var12 = 0.000010929193;
                        }
                    } else {
                        if (input[3] < 1.94) {
                            var12 = 0.00023158306;
                        } else {
                            var12 = -0.0005438613;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 3.38) {
                            var12 = -0.0004612152;
                        } else {
                            var12 = 0.00034099966;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var12 = -0.001134257;
                        } else {
                            var12 = 0.0012244544;
                        }
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[0] < 63.0) {
                        var12 = 0.000077813864;
                    } else {
                        var12 = -0.0005115668;
                    }
                } else {
                    var12 = 0.0032629538;
                }
            }
        }
        double var13;
        if (input[0] < 16.0) {
            if (input[1] < 5.0) {
                if (input[3] < 3.53) {
                    if (input[3] < 3.47) {
                        if (input[0] < 4.0) {
                            var13 = 0.00052828924;
                        } else {
                            var13 = -0.00020825087;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var13 = -0.00015915236;
                        } else {
                            var13 = -0.0028710922;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[3] < 4.16) {
                            var13 = 0.00053046836;
                        } else {
                            var13 = -0.00035856754;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var13 = -0.0002515091;
                        } else {
                            var13 = 0.0030009788;
                        }
                    }
                }
            } else {
                if (input[0] < 5.0) {
                    if (input[2] < 7.0) {
                        if (input[2] < 3.0) {
                            var13 = -0.00020137876;
                        } else {
                            var13 = -0.0021675122;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var13 = 0.0041030366;
                        } else {
                            var13 = -0.00072428584;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.84) {
                            var13 = 0.001058448;
                        } else {
                            var13 = -0.00016548947;
                        }
                    } else {
                        if (input[3] < 3.73) {
                            var13 = 0.0030196048;
                        } else {
                            var13 = 0.00060333527;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.49) {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.77) {
                            var13 = 0.00020961327;
                        } else {
                            var13 = 0.0012029664;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var13 = 0.00006823407;
                        } else {
                            var13 = -0.0010086399;
                        }
                    }
                } else {
                    if (input[3] < 1.26) {
                        if (input[1] < 2.0) {
                            var13 = 0.0010113049;
                        } else {
                            var13 = -0.0012672697;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var13 = -0.0005181161;
                        } else {
                            var13 = 0.00019281363;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[2] < 6.0) {
                        if (input[4] < 4.0) {
                            var13 = -0.0001064167;
                        } else {
                            var13 = 0.0013860542;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var13 = 0.00011313671;
                        } else {
                            var13 = -0.000998381;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.86) {
                            var13 = -0.00090317865;
                        } else {
                            var13 = 0.0014829318;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var13 = -0.00006089669;
                        } else {
                            var13 = 0.0039411266;
                        }
                    }
                }
            }
        }
        double var14;
        if (input[0] < 75.0) {
            if (input[3] < 3.2) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.27) {
                        if (input[1] < 5.0) {
                            var14 = 0.0005304275;
                        } else {
                            var14 = -0.0010044206;
                        }
                    } else {
                        if (input[3] < 1.85) {
                            var14 = -0.0008847133;
                        } else {
                            var14 = -0.00029259242;
                        }
                    }
                } else {
                    if (input[3] < 2.82) {
                        if (input[4] < 1.0) {
                            var14 = -0.0007325514;
                        } else {
                            var14 = 0.00040168493;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var14 = 0.00012260879;
                        } else {
                            var14 = -0.0013673155;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[2] < 10.0) {
                        if (input[3] < 4.81) {
                            var14 = -0.00013190345;
                        } else {
                            var14 = -0.00032877416;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var14 = 0.00000625036;
                        } else {
                            var14 = 0.00075547997;
                        }
                    }
                } else {
                    if (input[3] < 3.86) {
                        if (input[1] < 5.0) {
                            var14 = 0.000322552;
                        } else {
                            var14 = 0.0017502022;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var14 = -0.00015758666;
                        } else {
                            var14 = 0.0007155385;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[0] < 92.0) {
                    if (input[2] < 9.0) {
                        if (input[2] < 8.0) {
                            var14 = 0.00012666262;
                        } else {
                            var14 = -0.0007921508;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var14 = 0.000025222214;
                        } else {
                            var14 = 0.0016335453;
                        }
                    }
                } else {
                    if (input[3] < 3.09) {
                        if (input[0] < 97.0) {
                            var14 = -0.0013750072;
                        } else {
                            var14 = -0.000115966344;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var14 = 0.0004052169;
                        } else {
                            var14 = -0.0005864109;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[0] < 86.0) {
                            var14 = 0.00058684795;
                        } else {
                            var14 = 0.0019162095;
                        }
                    } else {
                        if (input[3] < 1.82) {
                            var14 = 0.0011352538;
                        } else {
                            var14 = -0.0024398405;
                        }
                    }
                } else {
                    if (input[3] < 3.79) {
                        if (input[3] < 3.36) {
                            var14 = -0.0002540336;
                        } else {
                            var14 = -0.0015409125;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var14 = -0.000744411;
                        } else {
                            var14 = 0.0008744565;
                        }
                    }
                }
            }
        }
        double var15;
        if (input[0] < 90.0) {
            if (input[1] < 5.0) {
                if (input[2] < 9.0) {
                    if (input[1] < 4.0) {
                        if (input[3] < 2.93) {
                            var15 = -0.00015048911;
                        } else {
                            var15 = 0.00017342271;
                        }
                    } else {
                        if (input[3] < 4.65) {
                            var15 = -0.00040932302;
                        } else {
                            var15 = 0.0007092788;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[4] < 5.0) {
                            var15 = 0.00007556374;
                        } else {
                            var15 = -0.0004654686;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var15 = -0.0002592795;
                        } else {
                            var15 = 0.002919222;
                        }
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[2] < 10.0) {
                        if (input[3] < 2.9) {
                            var15 = 0.0004522808;
                        } else {
                            var15 = -0.00015117662;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var15 = -0.00028413965;
                        } else {
                            var15 = -0.001938361;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var15 = -0.0003157966;
                        } else {
                            var15 = 0.0021565866;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var15 = 0.0029813282;
                        } else {
                            var15 = 0.0008618962;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 3.83) {
                if (input[3] < 3.27) {
                    if (input[4] < 6.0) {
                        if (input[3] < 1.07) {
                            var15 = 0.001553275;
                        } else {
                            var15 = -0.00038014262;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var15 = 0.0014101809;
                        } else {
                            var15 = -0.00023221076;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[0] < 99.0) {
                            var15 = -0.00020484359;
                        } else {
                            var15 = 0.0021763623;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var15 = -0.00032867046;
                        } else {
                            var15 = -0.002596706;
                        }
                    }
                }
            } else {
                if (input[4] < 7.0) {
                    if (input[1] < 4.0) {
                        if (input[2] < 6.0) {
                            var15 = -0.00006118807;
                        } else {
                            var15 = 0.0012421084;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var15 = -0.00066617824;
                        } else {
                            var15 = 0.0024383308;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[3] < 4.14) {
                            var15 = -0.0005331954;
                        } else {
                            var15 = -0.0031553574;
                        }
                    } else {
                        var15 = 0.0025684834;
                    }
                }
            }
        }
        double var16;
        if (input[0] < 16.0) {
            if (input[1] < 4.0) {
                if (input[0] < 4.0) {
                    if (input[0] < 1.0) {
                        if (input[1] < 2.0) {
                            var16 = -0.0015081579;
                        } else {
                            var16 = 0.00048023197;
                        }
                    } else {
                        if (input[3] < 1.7) {
                            var16 = -0.0005677423;
                        } else {
                            var16 = 0.0011148257;
                        }
                    }
                } else {
                    if (input[0] < 5.0) {
                        if (input[3] < 3.2) {
                            var16 = -0.0005268596;
                        } else {
                            var16 = -0.0022913418;
                        }
                    } else {
                        if (input[3] < 3.53) {
                            var16 = -0.00024686707;
                        } else {
                            var16 = 0.00027574477;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[3] < 3.8) {
                        if (input[3] < 3.51) {
                            var16 = 0.00024320844;
                        } else {
                            var16 = 0.0035479;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var16 = -0.00009799749;
                        } else {
                            var16 = -0.0020082442;
                        }
                    }
                } else {
                    if (input[3] < 3.76) {
                        if (input[2] < 10.0) {
                            var16 = 0.00023956239;
                        } else {
                            var16 = 0.0032370777;
                        }
                    } else {
                        if (input[2] < 2.0) {
                            var16 = -0.001504761;
                        } else {
                            var16 = 0.0043267473;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.49) {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[0] < 61.0) {
                            var16 = 0.000053821506;
                        } else {
                            var16 = 0.0008146941;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var16 = 0.00007667752;
                        } else {
                            var16 = -0.0009259125;
                        }
                    }
                } else {
                    if (input[3] < 1.43) {
                        if (input[1] < 2.0) {
                            var16 = 0.0006888895;
                        } else {
                            var16 = -0.0009037099;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var16 = -0.0005560802;
                        } else {
                            var16 = 0.00021287487;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[2] < 6.0) {
                        if (input[4] < 4.0) {
                            var16 = -0.00010175712;
                        } else {
                            var16 = 0.0013218704;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var16 = 0.00010430081;
                        } else {
                            var16 = -0.0009527008;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var16 = 0.0014718552;
                        } else {
                            var16 = -0.003602034;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var16 = -0.00053377467;
                        } else {
                            var16 = 0.0026784837;
                        }
                    }
                }
            }
        }
        double var17;
        if (input[0] < 75.0) {
            if (input[4] < 1.0) {
                if (input[3] < 1.45) {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.19) {
                            var17 = -0.0004282728;
                        } else {
                            var17 = 0.000818032;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var17 = 0.00006770243;
                        } else {
                            var17 = 0.0019530496;
                        }
                    }
                } else {
                    if (input[3] < 2.26) {
                        if (input[1] < 4.0) {
                            var17 = 0.00006015327;
                        } else {
                            var17 = -0.0029290551;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var17 = 0.00036400024;
                        } else {
                            var17 = -0.00028620224;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.82) {
                            var17 = -0.00017210885;
                        } else {
                            var17 = 0.0013101047;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var17 = 0.00018982857;
                        } else {
                            var17 = -0.000927729;
                        }
                    }
                } else {
                    if (input[3] < 1.45) {
                        if (input[2] < 5.0) {
                            var17 = -0.000080075544;
                        } else {
                            var17 = -0.00077884644;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var17 = -0.000057074743;
                        } else {
                            var17 = 0.00041810414;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[0] < 92.0) {
                    if (input[0] < 91.0) {
                        if (input[0] < 90.0) {
                            var17 = 0.00015524695;
                        } else {
                            var17 = -0.0010827685;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var17 = 0.00012059667;
                        } else {
                            var17 = 0.0027406595;
                        }
                    }
                } else {
                    if (input[3] < 3.07) {
                        if (input[3] < 3.0) {
                            var17 = -0.000572307;
                        } else {
                            var17 = -0.0047572614;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var17 = 0.0003624805;
                        } else {
                            var17 = -0.0005818137;
                        }
                    }
                }
            } else {
                if (input[3] < 4.92) {
                    if (input[1] < 5.0) {
                        if (input[3] < 1.79) {
                            var17 = 0.0002305754;
                        } else {
                            var17 = -0.0006808739;
                        }
                    } else {
                        if (input[0] < 86.0) {
                            var17 = 0.0010786434;
                        } else {
                            var17 = -0.00027338864;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        var17 = 0.0028486222;
                    } else {
                        var17 = -0.0005969644;
                    }
                }
            }
        }
        double var18;
        if (input[0] < 43.0) {
            if (input[1] < 5.0) {
                if (input[1] < 2.0) {
                    if (input[3] < 1.55) {
                        if (input[3] < 1.31) {
                            var18 = 0.0006299233;
                        } else {
                            var18 = 0.00011367397;
                        }
                    } else {
                        if (input[3] < 2.08) {
                            var18 = -0.0013245288;
                        } else {
                            var18 = -0.00018479464;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.99) {
                            var18 = -0.0011156256;
                        } else {
                            var18 = -0.000020897865;
                        }
                    } else {
                        if (input[3] < 1.62) {
                            var18 = -0.00034055588;
                        } else {
                            var18 = 0.00028758062;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[3] < 3.92) {
                        if (input[3] < 3.5) {
                            var18 = 0.00007194687;
                        } else {
                            var18 = 0.003163738;
                        }
                    } else {
                        if (input[0] < 39.0) {
                            var18 = -0.0010116644;
                        } else {
                            var18 = 0.001563201;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.78) {
                            var18 = -0.00039548706;
                        } else {
                            var18 = 0.0016622543;
                        }
                    } else {
                        if (input[3] < 4.06) {
                            var18 = 0.0014949922;
                        } else {
                            var18 = 0.0041123102;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.98) {
                if (input[2] < 9.0) {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.04) {
                            var18 = -0.00029824197;
                        } else {
                            var18 = 0.00001567661;
                        }
                    } else {
                        if (input[2] < 8.0) {
                            var18 = -0.00021337769;
                        } else {
                            var18 = -0.0011909059;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 3.38) {
                            var18 = -0.00041161344;
                        } else {
                            var18 = 0.0003150308;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var18 = -0.0010602439;
                        } else {
                            var18 = 0.0010922134;
                        }
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[0] < 63.0) {
                        var18 = 0.0000702262;
                    } else {
                        var18 = -0.0005274335;
                    }
                } else {
                    var18 = 0.0029415633;
                }
            }
        }
        double var19;
        if (input[0] < 29.0) {
            if (input[1] < 2.0) {
                if (input[0] < 1.0) {
                    if (input[4] < 1.0) {
                        var19 = 0.00004745722;
                    } else {
                        if (input[4] < 6.0) {
                            var19 = -0.0016863808;
                        } else {
                            var19 = -0.00039680302;
                        }
                    }
                } else {
                    if (input[0] < 2.0) {
                        if (input[2] < 7.0) {
                            var19 = 0.00028320434;
                        } else {
                            var19 = 0.0022757666;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var19 = -0.00010145359;
                        } else {
                            var19 = -0.0008604149;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[3] < 1.88) {
                        if (input[3] < 1.49) {
                            var19 = 0.000103182254;
                        } else {
                            var19 = 0.0019646385;
                        }
                    } else {
                        if (input[3] < 2.08) {
                            var19 = -0.003529811;
                        } else {
                            var19 = -0.00032668255;
                        }
                    }
                } else {
                    if (input[3] < 1.7) {
                        if (input[2] < 4.0) {
                            var19 = -0.00084483356;
                        } else {
                            var19 = 0.00005689313;
                        }
                    } else {
                        if (input[3] < 1.94) {
                            var19 = 0.001940489;
                        } else {
                            var19 = 0.0004177243;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.86) {
                if (input[2] < 9.0) {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.39) {
                            var19 = -0.00021818517;
                        } else {
                            var19 = 0.000046845107;
                        }
                    } else {
                        if (input[3] < 2.71) {
                            var19 = 0.00014760105;
                        } else {
                            var19 = -0.0005991476;
                        }
                    }
                } else {
                    if (input[0] < 31.0) {
                        if (input[3] < 4.58) {
                            var19 = -0.0023104926;
                        } else {
                            var19 = -0.00028563975;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var19 = -0.00029505143;
                        } else {
                            var19 = 0.0003670491;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var19 = -0.00047665933;
                        } else {
                            var19 = 0.0033056259;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var19 = 0.000077161494;
                        } else {
                            var19 = -0.0012170685;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 3.0) {
                            var19 = 0.0021095197;
                        } else {
                            var19 = -0.00049434666;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var19 = 0.0000786384;
                        } else {
                            var19 = 0.0030367703;
                        }
                    }
                }
            }
        }
        double var20;
        if (input[0] < 75.0) {
            if (input[4] < 1.0) {
                if (input[3] < 1.45) {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.19) {
                            var20 = -0.00037115786;
                        } else {
                            var20 = 0.00080838456;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var20 = 0.000087447464;
                        } else {
                            var20 = 0.0018912544;
                        }
                    }
                } else {
                    if (input[3] < 2.31) {
                        if (input[1] < 4.0) {
                            var20 = -0.000066563276;
                        } else {
                            var20 = -0.0027463126;
                        }
                    } else {
                        if (input[3] < 2.39) {
                            var20 = 0.0015663079;
                        } else {
                            var20 = -0.00017101858;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[3] < 3.77) {
                        if (input[3] < 3.42) {
                            var20 = -0.00012672314;
                        } else {
                            var20 = -0.000775363;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var20 = 0.000642986;
                        } else {
                            var20 = -0.00025210934;
                        }
                    }
                } else {
                    if (input[3] < 1.16) {
                        if (input[2] < 4.0) {
                            var20 = 0.00008353385;
                        } else {
                            var20 = -0.0019487737;
                        }
                    } else {
                        if (input[3] < 2.71) {
                            var20 = 0.00058116426;
                        } else {
                            var20 = 0.000028671944;
                        }
                    }
                }
            }
        } else {
            if (input[1] < 2.0) {
                if (input[2] < 2.0) {
                    if (input[4] < 4.0) {
                        if (input[3] < 4.33) {
                            var20 = 0.0003833;
                        } else {
                            var20 = -0.00027322292;
                        }
                    } else {
                        if (input[0] < 84.0) {
                            var20 = 0.0004952452;
                        } else {
                            var20 = 0.002410563;
                        }
                    }
                } else {
                    if (input[0] < 96.0) {
                        if (input[4] < 4.0) {
                            var20 = 0.00012618347;
                        } else {
                            var20 = -0.00077433913;
                        }
                    } else {
                        if (input[3] < 3.76) {
                            var20 = 0.00041638172;
                        } else {
                            var20 = 0.0020752216;
                        }
                    }
                }
            } else {
                if (input[2] < 3.0) {
                    if (input[3] < 1.72) {
                        if (input[3] < 1.48) {
                            var20 = 0.0016744972;
                        } else {
                            var20 = 0.00029489884;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var20 = 0.00027016425;
                        } else {
                            var20 = -0.0016507294;
                        }
                    }
                } else {
                    if (input[3] < 1.43) {
                        if (input[2] < 4.0) {
                            var20 = 0.001889053;
                        } else {
                            var20 = -0.0012758787;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var20 = -0.00065987714;
                        } else {
                            var20 = 0.00010005264;
                        }
                    }
                }
            }
        }
        double var21;
        if (input[1] < 5.0) {
            if (input[2] < 9.0) {
                if (input[1] < 4.0) {
                    if (input[4] < 6.0) {
                        if (input[2] < 4.0) {
                            var21 = -0.00012696546;
                        } else {
                            var21 = 0.00022569286;
                        }
                    } else {
                        if (input[2] < 8.0) {
                            var21 = -0.0001283943;
                        } else {
                            var21 = -0.0011331424;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.65) {
                            var21 = 0.00024474759;
                        } else {
                            var21 = -0.0007639008;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var21 = -0.0043293624;
                        } else {
                            var21 = -0.00047130082;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.0) {
                        if (input[3] < 3.95) {
                            var21 = -0.00020833882;
                        } else {
                            var21 = -0.0026953781;
                        }
                    } else {
                        if (input[3] < 4.29) {
                            var21 = 0.0015942429;
                        } else {
                            var21 = -0.000006755317;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.74) {
                            var21 = 0.0019895972;
                        } else {
                            var21 = -0.0009402184;
                        }
                    } else {
                        if (input[0] < 25.0) {
                            var21 = 0.005047495;
                        } else {
                            var21 = 0.0027870599;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[3] < 3.88) {
                    if (input[3] < 3.58) {
                        if (input[2] < 10.0) {
                            var21 = 0.00011602911;
                        } else {
                            var21 = -0.0014423593;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var21 = 0.00029877425;
                        } else {
                            var21 = 0.0035447981;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 4.22) {
                            var21 = -0.00036684118;
                        } else {
                            var21 = 0.00028461198;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var21 = 0.0006922861;
                        } else {
                            var21 = -0.0028859985;
                        }
                    }
                }
            } else {
                if (input[3] < 4.03) {
                    if (input[0] < 43.0) {
                        if (input[2] < 4.0) {
                            var21 = -0.00007280249;
                        } else {
                            var21 = 0.001348717;
                        }
                    } else {
                        if (input[3] < 1.04) {
                            var21 = -0.0031935216;
                        } else {
                            var21 = -0.00021111929;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.4) {
                            var21 = -0.0020899614;
                        } else {
                            var21 = 0.0005462289;
                        }
                    } else {
                        if (input[3] < 4.32) {
                            var21 = 0.0020069217;
                        } else {
                            var21 = 0.0042171297;
                        }
                    }
                }
            }
        }
        double var22;
        if (input[0] < 90.0) {
            if (input[1] < 5.0) {
                if (input[2] < 9.0) {
                    if (input[1] < 4.0) {
                        if (input[3] < 2.93) {
                            var22 = -0.00012444389;
                        } else {
                            var22 = 0.00015787939;
                        }
                    } else {
                        if (input[3] < 4.65) {
                            var22 = -0.0003711881;
                        } else {
                            var22 = 0.000672787;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[4] < 5.0) {
                            var22 = 0.000079752295;
                        } else {
                            var22 = -0.00043186615;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var22 = 0.00039205002;
                        } else {
                            var22 = 0.0037254484;
                        }
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[2] < 10.0) {
                        if (input[3] < 2.9) {
                            var22 = 0.00042940062;
                        } else {
                            var22 = -0.0001754402;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var22 = -0.0002018935;
                        } else {
                            var22 = -0.0018234635;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var22 = -0.0002639667;
                        } else {
                            var22 = 0.0020183183;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var22 = 0.0026864128;
                        } else {
                            var22 = 0.0007249463;
                        }
                    }
                }
            }
        } else {
            if (input[2] < 3.0) {
                if (input[3] < 1.72) {
                    if (input[3] < 1.31) {
                        if (input[1] < 2.0) {
                            var22 = 0.00012635192;
                        } else {
                            var22 = 0.0019014378;
                        }
                    } else {
                        if (input[0] < 92.0) {
                            var22 = 0.00005333821;
                        } else {
                            var22 = 0.00078605674;
                        }
                    }
                } else {
                    if (input[4] < 2.0) {
                        if (input[3] < 3.43) {
                            var22 = 0.0009485477;
                        } else {
                            var22 = 0.000027304462;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var22 = 0.00064246514;
                        } else {
                            var22 = -0.0021136575;
                        }
                    }
                }
            } else {
                if (input[3] < 1.85) {
                    if (input[3] < 1.82) {
                        if (input[2] < 4.0) {
                            var22 = 0.0017115352;
                        } else {
                            var22 = -0.000770766;
                        }
                    } else {
                        var22 = -0.0052598654;
                    }
                } else {
                    if (input[0] < 100.0) {
                        if (input[4] < 1.0) {
                            var22 = -0.00037342357;
                        } else {
                            var22 = 0.00025946653;
                        }
                    } else {
                        if (input[3] < 2.26) {
                            var22 = 0.0022688867;
                        } else {
                            var22 = -0.0010447641;
                        }
                    }
                }
            }
        }
        double var23;
        if (input[0] < 12.0) {
            if (input[1] < 2.0) {
                if (input[3] < 4.11) {
                    if (input[3] < 3.9) {
                        if (input[3] < 1.77) {
                            var23 = 0.0006208652;
                        } else {
                            var23 = -0.00057874963;
                        }
                    } else {
                        var23 = 0.0023221946;
                    }
                } else {
                    if (input[4] < 5.0) {
                        if (input[2] < 1.0) {
                            var23 = 0.000979501;
                        } else {
                            var23 = -0.0002496217;
                        }
                    } else {
                        var23 = -0.003029058;
                    }
                }
            } else {
                if (input[0] < 2.0) {
                    if (input[3] < 2.99) {
                        if (input[3] < 2.56) {
                            var23 = -0.0002579136;
                        } else {
                            var23 = 0.0020888092;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var23 = 0.00034592662;
                        } else {
                            var23 = -0.001659392;
                        }
                    }
                } else {
                    if (input[4] < 5.0) {
                        if (input[3] < 2.88) {
                            var23 = 0.0006288856;
                        } else {
                            var23 = -0.00028518506;
                        }
                    } else {
                        if (input[3] < 3.37) {
                            var23 = 0.00034418242;
                        } else {
                            var23 = 0.0022014906;
                        }
                    }
                }
            }
        } else {
            if (input[2] < 3.0) {
                if (input[1] < 2.0) {
                    if (input[3] < 3.86) {
                        if (input[3] < 3.37) {
                            var23 = 0.0003815169;
                        } else {
                            var23 = -0.00073473237;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var23 = -0.00022324365;
                        } else {
                            var23 = 0.0019793021;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.74) {
                            var23 = -0.000567907;
                        } else {
                            var23 = 0.00044626492;
                        }
                    } else {
                        if (input[3] < 3.62) {
                            var23 = -0.00027258784;
                        } else {
                            var23 = -0.0017711271;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 1.47) {
                        if (input[1] < 5.0) {
                            var23 = 0.0001665968;
                        } else {
                            var23 = 0.0019368392;
                        }
                    } else {
                        if (input[3] < 2.33) {
                            var23 = -0.001842751;
                        } else {
                            var23 = -0.000080521626;
                        }
                    }
                } else {
                    if (input[3] < 1.43) {
                        if (input[1] < 2.0) {
                            var23 = 0.0005698136;
                        } else {
                            var23 = -0.0007532662;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var23 = -0.00032035526;
                        } else {
                            var23 = 0.00025556367;
                        }
                    }
                }
            }
        }
        double var24;
        if (input[0] < 90.0) {
            if (input[1] < 5.0) {
                if (input[2] < 9.0) {
                    if (input[1] < 4.0) {
                        if (input[3] < 2.93) {
                            var24 = -0.00011529517;
                        } else {
                            var24 = 0.00014638861;
                        }
                    } else {
                        if (input[3] < 4.68) {
                            var24 = -0.00034532236;
                        } else {
                            var24 = 0.00070092286;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[4] < 5.0) {
                            var24 = 0.000077434386;
                        } else {
                            var24 = -0.00041441788;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var24 = -0.00029271175;
                        } else {
                            var24 = 0.002460326;
                        }
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[2] < 10.0) {
                        if (input[3] < 2.9) {
                            var24 = 0.00041023115;
                        } else {
                            var24 = -0.00017294413;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var24 = -0.00018848777;
                        } else {
                            var24 = -0.0017396543;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var24 = -0.00023703468;
                        } else {
                            var24 = 0.0019678927;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var24 = 0.0025462322;
                        } else {
                            var24 = 0.0006780496;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 1.07) {
                if (input[1] < 2.0) {
                    var24 = 0.00007256419;
                } else {
                    var24 = 0.0017466486;
                }
            } else {
                if (input[3] < 1.08) {
                    var24 = -0.003271538;
                } else {
                    if (input[2] < 3.0) {
                        if (input[3] < 1.72) {
                            var24 = 0.0010764616;
                        } else {
                            var24 = -0.000923327;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var24 = -0.0007214974;
                        } else {
                            var24 = 0.000039900955;
                        }
                    }
                }
            }
        }
        double var25;
        if (input[0] < 43.0) {
            if (input[1] < 5.0) {
                if (input[0] < 1.0) {
                    if (input[3] < 1.77) {
                        if (input[3] < 1.67) {
                            var25 = 0.000018191338;
                        } else {
                            var25 = 0.0012489051;
                        }
                    } else {
                        if (input[2] < 1.0) {
                            var25 = 0.0013091088;
                        } else {
                            var25 = -0.0013161001;
                        }
                    }
                } else {
                    if (input[0] < 4.0) {
                        if (input[3] < 1.15) {
                            var25 = -0.0016982064;
                        } else {
                            var25 = 0.0008948914;
                        }
                    } else {
                        if (input[0] < 5.0) {
                            var25 = -0.0007959224;
                        } else {
                            var25 = 0.000000036971873;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[3] < 3.92) {
                        if (input[3] < 3.5) {
                            var25 = 0.00004384697;
                        } else {
                            var25 = 0.0029272807;
                        }
                    } else {
                        if (input[0] < 39.0) {
                            var25 = -0.0009190451;
                        } else {
                            var25 = 0.0014919686;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.78) {
                            var25 = -0.0003333625;
                        } else {
                            var25 = 0.0015168588;
                        }
                    } else {
                        if (input[3] < 4.06) {
                            var25 = 0.0012279071;
                        } else {
                            var25 = 0.0036131383;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.98) {
                if (input[2] < 9.0) {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.04) {
                            var25 = -0.0002545597;
                        } else {
                            var25 = 0.000021427268;
                        }
                    } else {
                        if (input[2] < 8.0) {
                            var25 = -0.0001789874;
                        } else {
                            var25 = -0.00111613;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[4] < 7.0) {
                            var25 = -0.0000014132454;
                        } else {
                            var25 = -0.0010215085;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var25 = -0.000958852;
                        } else {
                            var25 = 0.00094270793;
                        }
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[0] < 63.0) {
                        var25 = 0.000046181678;
                    } else {
                        var25 = -0.00045915047;
                    }
                } else {
                    var25 = 0.0026931858;
                }
            }
        }
        double var26;
        if (input[0] < 75.0) {
            if (input[4] < 1.0) {
                if (input[3] < 1.51) {
                    if (input[3] < 1.06) {
                        if (input[1] < 2.0) {
                            var26 = 0.00048140786;
                        } else {
                            var26 = -0.0013454274;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var26 = 0.000138421;
                        } else {
                            var26 = 0.001222924;
                        }
                    }
                } else {
                    if (input[3] < 2.26) {
                        if (input[1] < 4.0) {
                            var26 = 0.00014605792;
                        } else {
                            var26 = -0.0028050747;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var26 = 0.00035950434;
                        } else {
                            var26 = -0.00023853184;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.82) {
                            var26 = -0.00012306617;
                        } else {
                            var26 = 0.0011541097;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var26 = 0.00019706879;
                        } else {
                            var26 = -0.00083122525;
                        }
                    }
                } else {
                    if (input[3] < 1.62) {
                        if (input[1] < 3.0) {
                            var26 = -0.00076980627;
                        } else {
                            var26 = -0.00015012165;
                        }
                    } else {
                        if (input[3] < 1.94) {
                            var26 = 0.0011340221;
                        } else {
                            var26 = 0.0001314086;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[0] < 92.0) {
                    if (input[2] < 9.0) {
                        if (input[2] < 8.0) {
                            var26 = 0.00013899565;
                        } else {
                            var26 = -0.00072605064;
                        }
                    } else {
                        if (input[3] < 2.56) {
                            var26 = 0.0014041261;
                        } else {
                            var26 = -0.0000036980862;
                        }
                    }
                } else {
                    if (input[3] < 3.09) {
                        if (input[0] < 97.0) {
                            var26 = -0.0011923261;
                        } else {
                            var26 = -0.000020243113;
                        }
                    } else {
                        if (input[3] < 3.13) {
                            var26 = 0.0014340207;
                        } else {
                            var26 = 0.00021011829;
                        }
                    }
                }
            } else {
                if (input[0] < 83.0) {
                    if (input[1] < 5.0) {
                        if (input[3] < 4.73) {
                            var26 = -0.0010193749;
                        } else {
                            var26 = 0.0012653542;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var26 = 0.0016472603;
                        } else {
                            var26 = -0.00038653793;
                        }
                    }
                } else {
                    if (input[0] < 99.0) {
                        if (input[2] < 9.0) {
                            var26 = -0.00021414633;
                        } else {
                            var26 = 0.00068832823;
                        }
                    } else {
                        if (input[3] < 3.03) {
                            var26 = 0.0010368084;
                        } else {
                            var26 = -0.0020879633;
                        }
                    }
                }
            }
        }
        double var27;
        if (input[0] < 1.0) {
            if (input[2] < 3.0) {
                if (input[4] < 5.0) {
                    if (input[4] < 3.0) {
                        var27 = -0.00040856205;
                    } else {
                        if (input[3] < 2.56) {
                            var27 = 0.0004150033;
                        } else {
                            var27 = 0.0020822564;
                        }
                    }
                } else {
                    var27 = -0.00082046987;
                }
            } else {
                if (input[3] < 1.77) {
                    if (input[3] < 1.67) {
                        if (input[2] < 9.0) {
                            var27 = -0.000024610757;
                        } else {
                            var27 = 0.000041881205;
                        }
                    } else {
                        var27 = 0.0011893302;
                    }
                } else {
                    if (input[3] < 3.36) {
                        if (input[4] < 3.0) {
                            var27 = -0.0022848598;
                        } else {
                            var27 = -0.0010197307;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var27 = 0.0001928111;
                        } else {
                            var27 = -0.0010217209;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 4.0) {
                if (input[0] < 2.0) {
                    if (input[2] < 7.0) {
                        if (input[1] < 4.0) {
                            var27 = 0.000066711815;
                        } else {
                            var27 = -0.0018799835;
                        }
                    } else {
                        if (input[2] < 8.0) {
                            var27 = 0.0020724735;
                        } else {
                            var27 = -0.00016737646;
                        }
                    }
                } else {
                    if (input[2] < 2.0) {
                        if (input[3] < 1.7) {
                            var27 = -0.0003177941;
                        } else {
                            var27 = 0.0031053857;
                        }
                    } else {
                        if (input[3] < 2.66) {
                            var27 = 0.0017653117;
                        } else {
                            var27 = -0.00027477054;
                        }
                    }
                }
            } else {
                if (input[0] < 5.0) {
                    if (input[3] < 3.47) {
                        if (input[2] < 1.0) {
                            var27 = -0.001905545;
                        } else {
                            var27 = -0.00012689504;
                        }
                    } else {
                        if (input[3] < 4.36) {
                            var27 = -0.0025656896;
                        } else {
                            var27 = -0.00005996625;
                        }
                    }
                } else {
                    if (input[0] < 12.0) {
                        if (input[1] < 5.0) {
                            var27 = 0.000074888274;
                        } else {
                            var27 = 0.0014453671;
                        }
                    } else {
                        if (input[2] < 3.0) {
                            var27 = -0.00015458382;
                        } else {
                            var27 = 0.000026606946;
                        }
                    }
                }
            }
        }
        return 0.5 + (subroutine0(input) + var0 + var1 + var2 + var3 + var4 + var5 + var6 + var7 + var8 + var9 + var10 + var11 + var12 + var13 + var14 + var15 + var16 + var17 + var18 + var19 + var20 + var21 + var22 + var23 + var24 + var25 + var26 + var27);
    }
    public static double subroutine0(double[] input) {
        double var0;
        if (input[3] < 2.42) {
            if (input[2] < 4.0) {
                if (input[3] < 1.31) {
                    if (input[0] < 48.0) {
                        if (input[1] < 5.0) {
                            var0 = -0.00058913644;
                        } else {
                            var0 = -0.0018893763;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var0 = 0.0006830957;
                        } else {
                            var0 = -0.00077557564;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[0] < 11.0) {
                            var0 = -0.0021885184;
                        } else {
                            var0 = -0.00017066419;
                        }
                    } else {
                        if (input[0] < 30.0) {
                            var0 = -0.0006146608;
                        } else {
                            var0 = -0.0017702011;
                        }
                    }
                }
            } else {
                if (input[3] < 1.45) {
                    if (input[1] < 2.0) {
                        if (input[3] < 1.31) {
                            var0 = 0.00071247213;
                        } else {
                            var0 = -0.0008196556;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var0 = -0.0031854801;
                        } else {
                            var0 = -0.0009907217;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[1] < 4.0) {
                            var0 = -0.0005465906;
                        } else {
                            var0 = -0.0051820544;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var0 = -0.0017448418;
                        } else {
                            var0 = 0.0011106181;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 19.0) {
                if (input[4] < 4.0) {
                    if (input[2] < 10.0) {
                        if (input[4] < 2.0) {
                            var0 = -0.00040573563;
                        } else {
                            var0 = 0.0007918955;
                        }
                    } else {
                        if (input[0] < 7.0) {
                            var0 = -0.0027996886;
                        } else {
                            var0 = -0.000030263267;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.81) {
                            var0 = -0.0017749232;
                        } else {
                            var0 = 0.0044411463;
                        }
                    } else {
                        if (input[3] < 3.37) {
                            var0 = 0.0016194185;
                        } else {
                            var0 = 0.0036176324;
                        }
                    }
                }
            } else {
                if (input[3] < 4.49) {
                    if (input[2] < 9.0) {
                        if (input[0] < 100.0) {
                            var0 = -0.00024505184;
                        } else {
                            var0 = -0.0029979872;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var0 = -0.0005437011;
                        } else {
                            var0 = 0.0017096931;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[2] < 6.0) {
                            var0 = 0.0009515115;
                        } else {
                            var0 = -0.00006892378;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var0 = -0.0020465173;
                        } else {
                            var0 = 0.00414052;
                        }
                    }
                }
            }
        }
        double var1;
        if (input[3] < 3.2) {
            if (input[2] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[3] < 2.61) {
                        if (input[1] < 3.0) {
                            var1 = -0.00025730507;
                        } else {
                            var1 = -0.0011663677;
                        }
                    } else {
                        if (input[0] < 52.0) {
                            var1 = -0.00015402987;
                        } else {
                            var1 = 0.0018288108;
                        }
                    }
                } else {
                    if (input[0] < 19.0) {
                        if (input[3] < 2.14) {
                            var1 = -0.0009324319;
                        } else {
                            var1 = 0.00074457994;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var1 = -0.00020421916;
                        } else {
                            var1 = -0.001441076;
                        }
                    }
                }
            } else {
                if (input[3] < 1.45) {
                    if (input[1] < 2.0) {
                        if (input[3] < 1.31) {
                            var1 = 0.00067786704;
                        } else {
                            var1 = -0.00078182406;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var1 = -0.0030300894;
                        } else {
                            var1 = -0.0009415539;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[1] < 4.0) {
                            var1 = -0.00082273147;
                        } else {
                            var1 = -0.00305053;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var1 = -0.0012462403;
                        } else {
                            var1 = 0.0008924568;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 60.0) {
                if (input[4] < 4.0) {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.79) {
                            var1 = 0.0019092693;
                        } else {
                            var1 = 0.000119948985;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var1 = 0.00016381805;
                        } else {
                            var1 = -0.0008986934;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var1 = 0.0012402276;
                        } else {
                            var1 = -0.0028134421;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var1 = 0.0015815037;
                        } else {
                            var1 = 0.004101393;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 3.0) {
                            var1 = -0.000030000674;
                        } else {
                            var1 = 0.002305744;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var1 = 0.00008678613;
                        } else {
                            var1 = -0.0038407897;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[4] < 5.0) {
                            var1 = 0.00028496273;
                        } else {
                            var1 = -0.0022444369;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var1 = 0.000029050143;
                        } else {
                            var1 = 0.0013839368;
                        }
                    }
                }
            }
        }
        double var2;
        if (input[3] < 4.4) {
            if (input[2] < 9.0) {
                if (input[0] < 41.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var2 = -0.00018049133;
                        } else {
                            var2 = -0.001362644;
                        }
                    } else {
                        if (input[3] < 1.62) {
                            var2 = -0.0007867814;
                        } else {
                            var2 = 0.00064016803;
                        }
                    }
                } else {
                    if (input[0] < 90.0) {
                        if (input[1] < 5.0) {
                            var2 = -0.0006141343;
                        } else {
                            var2 = 0.00015751149;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var2 = -0.00032213106;
                        } else {
                            var2 = -0.0016717388;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.0) {
                        if (input[0] < 91.0) {
                            var2 = -0.0004858154;
                        } else {
                            var2 = -0.0017746253;
                        }
                    } else {
                        if (input[3] < 4.3) {
                            var2 = 0.0024759246;
                        } else {
                            var2 = -0.0003344509;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.78) {
                            var2 = 0.001090608;
                        } else {
                            var2 = -0.0019759198;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var2 = 0.0035464496;
                        } else {
                            var2 = 0.006055557;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[1] < 3.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var2 = -0.00030530783;
                        } else {
                            var2 = 0.00012198251;
                        }
                    } else {
                        if (input[3] < 4.96) {
                            var2 = 0.0025470748;
                        } else {
                            var2 = 0.005330699;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 4.0) {
                            var2 = -0.0000098716955;
                        } else {
                            var2 = 0.002574169;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var2 = 0.00051923445;
                        } else {
                            var2 = -0.00253272;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 75.0) {
                        if (input[3] < 4.78) {
                            var2 = 0.00016607571;
                        } else {
                            var2 = 0.003488832;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var2 = -0.0053271623;
                        } else {
                            var2 = -0.002170238;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var2 = -0.0048851087;
                        } else {
                            var2 = 0.0012812308;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var2 = 0.004562688;
                        } else {
                            var2 = 0.0089973165;
                        }
                    }
                }
            }
        }
        double var3;
        if (input[3] < 4.4) {
            if (input[2] < 9.0) {
                if (input[0] < 41.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var3 = -0.00017157725;
                        } else {
                            var3 = -0.0012951739;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var3 = -0.001218722;
                        } else {
                            var3 = 0.0005307119;
                        }
                    }
                } else {
                    if (input[0] < 75.0) {
                        if (input[1] < 5.0) {
                            var3 = -0.00048660184;
                        } else {
                            var3 = 0.00021724652;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var3 = -0.00028996766;
                        } else {
                            var3 = -0.0012538593;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.0) {
                        if (input[0] < 90.0) {
                            var3 = -0.00045872433;
                        } else {
                            var3 = -0.0016690989;
                        }
                    } else {
                        if (input[3] < 4.3) {
                            var3 = 0.002360379;
                        } else {
                            var3 = -0.00031924955;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.77) {
                            var3 = 0.0010735398;
                        } else {
                            var3 = -0.0018487595;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var3 = 0.00067560206;
                        } else {
                            var3 = 0.0049136095;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[1] < 3.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var3 = -0.00029037258;
                        } else {
                            var3 = 0.000115996365;
                        }
                    } else {
                        if (input[3] < 4.96) {
                            var3 = 0.002423075;
                        } else {
                            var3 = 0.0051022377;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 4.0) {
                            var3 = -0.000009392137;
                        } else {
                            var3 = 0.002451055;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var3 = 0.0004935761;
                        } else {
                            var3 = -0.0024087338;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 75.0) {
                        if (input[0] < 20.0) {
                            var3 = 0.0040839077;
                        } else {
                            var3 = 0.0003376058;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var3 = -0.0051273913;
                        } else {
                            var3 = -0.0020888539;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var3 = -0.0046652746;
                        } else {
                            var3 = 0.0012263196;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var3 = 0.0043497547;
                        } else {
                            var3 = 0.008564747;
                        }
                    }
                }
            }
        }
        double var4;
        if (input[3] < 2.42) {
            if (input[0] < 25.0) {
                if (input[2] < 4.0) {
                    if (input[3] < 2.39) {
                        if (input[3] < 2.14) {
                            var4 = -0.00087448856;
                        } else {
                            var4 = 0.00049877464;
                        }
                    } else {
                        var4 = -0.0036930174;
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 1.6) {
                            var4 = -0.0010156343;
                        } else {
                            var4 = 0.00013581263;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var4 = -0.0026292414;
                        } else {
                            var4 = 0.0018020867;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 76.0) {
                        if (input[2] < 9.0) {
                            var4 = -0.0006843522;
                        } else {
                            var4 = -0.00000328174;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var4 = -0.00027160154;
                        } else {
                            var4 = -0.0012231045;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[0] < 31.0) {
                            var4 = -0.002816701;
                        } else {
                            var4 = -0.00039138467;
                        }
                    } else {
                        if (input[3] < 1.55) {
                            var4 = -0.0010357816;
                        } else {
                            var4 = 0.002473928;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 19.0) {
                if (input[4] < 4.0) {
                    if (input[3] < 2.68) {
                        if (input[2] < 7.0) {
                            var4 = -0.00031274834;
                        } else {
                            var4 = 0.0037385921;
                        }
                    } else {
                        if (input[3] < 3.08) {
                            var4 = -0.0012102473;
                        } else {
                            var4 = 0.00009403987;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.81) {
                            var4 = -0.0015814056;
                        } else {
                            var4 = 0.003971557;
                        }
                    } else {
                        if (input[0] < 2.0) {
                            var4 = -0.000557569;
                        } else {
                            var4 = 0.002809798;
                        }
                    }
                }
            } else {
                if (input[3] < 4.86) {
                    if (input[2] < 9.0) {
                        if (input[0] < 100.0) {
                            var4 = -0.00011662303;
                        } else {
                            var4 = -0.00268359;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var4 = 0.000044008368;
                        } else {
                            var4 = 0.0019819413;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 2.0) {
                            var4 = 0.0021407122;
                        } else {
                            var4 = 0.00022462875;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var4 = 0.0015465515;
                        } else {
                            var4 = 0.007591589;
                        }
                    }
                }
            }
        }
        double var5;
        if (input[3] < 3.2) {
            if (input[2] < 4.0) {
                if (input[1] < 2.0) {
                    if (input[0] < 29.0) {
                        if (input[3] < 1.83) {
                            var5 = 0.00006264536;
                        } else {
                            var5 = -0.0015718461;
                        }
                    } else {
                        if (input[3] < 2.8) {
                            var5 = 0.000018389926;
                        } else {
                            var5 = 0.0025114769;
                        }
                    }
                } else {
                    if (input[3] < 1.31) {
                        if (input[0] < 69.0) {
                            var5 = -0.00059821195;
                        } else {
                            var5 = 0.0010388687;
                        }
                    } else {
                        if (input[0] < 30.0) {
                            var5 = -0.00027709463;
                        } else {
                            var5 = -0.0013740204;
                        }
                    }
                }
            } else {
                if (input[3] < 1.45) {
                    if (input[1] < 2.0) {
                        if (input[3] < 1.31) {
                            var5 = 0.0007300706;
                        } else {
                            var5 = -0.000657;
                        }
                    } else {
                        if (input[3] < 1.15) {
                            var5 = -0.0025827454;
                        } else {
                            var5 = -0.0007828129;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[1] < 4.0) {
                            var5 = -0.00072158285;
                        } else {
                            var5 = -0.0028799204;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var5 = -0.0010947953;
                        } else {
                            var5 = 0.00082743587;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 60.0) {
                if (input[4] < 4.0) {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.79) {
                            var5 = 0.0018140016;
                        } else {
                            var5 = 0.000112142894;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var5 = -0.0008524721;
                        } else {
                            var5 = 0.00016863638;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var5 = 0.0011890362;
                        } else {
                            var5 = -0.0026295795;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var5 = 0.0013624941;
                        } else {
                            var5 = 0.003550668;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 3.0) {
                            var5 = 0.0000136892;
                        } else {
                            var5 = 0.0022031288;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var5 = 0.00012577382;
                        } else {
                            var5 = -0.0035696432;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var5 = 0.00046774765;
                        } else {
                            var5 = -0.001805098;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var5 = 0.000051594554;
                        } else {
                            var5 = 0.0012169186;
                        }
                    }
                }
            }
        }
        double var6;
        if (input[3] < 4.4) {
            if (input[1] < 5.0) {
                if (input[2] < 9.0) {
                    if (input[0] < 31.0) {
                        if (input[1] < 2.0) {
                            var6 = -0.00079308817;
                        } else {
                            var6 = 0.0002869157;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var6 = -0.00027959986;
                        } else {
                            var6 = -0.000921037;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.0) {
                            var6 = -0.0006038678;
                        } else {
                            var6 = 0.0011261072;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var6 = -0.00008319297;
                        } else {
                            var6 = 0.0053452738;
                        }
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[0] < 14.0) {
                        if (input[0] < 13.0) {
                            var6 = 0.0004510645;
                        } else {
                            var6 = 0.003649621;
                        }
                    } else {
                        if (input[2] < 10.0) {
                            var6 = -0.0004406137;
                        } else {
                            var6 = -0.00202593;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.48) {
                            var6 = -0.00047004508;
                        } else {
                            var6 = -0.0028335373;
                        }
                    } else {
                        if (input[3] < 1.55) {
                            var6 = -0.0007859732;
                        } else {
                            var6 = 0.002961261;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[1] < 3.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var6 = -0.00029232734;
                        } else {
                            var6 = 0.000093475675;
                        }
                    } else {
                        if (input[0] < 89.0) {
                            var6 = 0.0023082928;
                        } else {
                            var6 = 0.004800299;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 4.0) {
                            var6 = -0.000013239616;
                        } else {
                            var6 = 0.0023228491;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var6 = 0.00046161562;
                        } else {
                            var6 = -0.0023903223;
                        }
                    }
                }
            } else {
                if (input[0] < 23.0) {
                    if (input[1] < 2.0) {
                        if (input[0] < 12.0) {
                            var6 = -0.0017779112;
                        } else {
                            var6 = 0.00048806073;
                        }
                    } else {
                        if (input[3] < 4.41) {
                            var6 = 0.00003091097;
                        } else {
                            var6 = 0.0077256695;
                        }
                    }
                } else {
                    if (input[3] < 4.86) {
                        if (input[1] < 4.0) {
                            var6 = -0.0010535324;
                        } else {
                            var6 = 0.0021413506;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var6 = 0.0014935852;
                        } else {
                            var6 = 0.0071922923;
                        }
                    }
                }
            }
        }
        double var7;
        if (input[3] < 2.18) {
            if (input[0] < 76.0) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.27) {
                        if (input[1] < 5.0) {
                            var7 = -0.000052202162;
                        } else {
                            var7 = -0.0014798278;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var7 = -0.00045937594;
                        } else {
                            var7 = -0.0012207556;
                        }
                    }
                } else {
                    if (input[3] < 1.26) {
                        if (input[1] < 2.0) {
                            var7 = 0.00071505405;
                        } else {
                            var7 = -0.0016463535;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var7 = -0.0015919196;
                        } else {
                            var7 = 0.00076496013;
                        }
                    }
                }
            } else {
                if (input[3] < 1.91) {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.72) {
                            var7 = 0.0005360519;
                        } else {
                            var7 = -0.0014151236;
                        }
                    } else {
                        if (input[3] < 1.85) {
                            var7 = -0.0013338131;
                        } else {
                            var7 = 0.0029622496;
                        }
                    }
                } else {
                    if (input[3] < 2.06) {
                        if (input[1] < 2.0) {
                            var7 = -0.00059869664;
                        } else {
                            var7 = -0.0035765138;
                        }
                    } else {
                        if (input[2] < 10.0) {
                            var7 = -0.0012867671;
                        } else {
                            var7 = 0.0023764134;
                        }
                    }
                }
            }
        } else {
            if (input[1] < 5.0) {
                if (input[3] < 4.66) {
                    if (input[2] < 9.0) {
                        if (input[4] < 6.0) {
                            var7 = -0.000017434497;
                        } else {
                            var7 = -0.00074150966;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var7 = -0.00017936646;
                        } else {
                            var7 = 0.002538439;
                        }
                    }
                } else {
                    if (input[4] < 7.0) {
                        if (input[4] < 4.0) {
                            var7 = 0.00011280816;
                        } else {
                            var7 = 0.0013153935;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var7 = 0.0006511927;
                        } else {
                            var7 = 0.005371707;
                        }
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[0] < 16.0) {
                        if (input[0] < 13.0) {
                            var7 = 0.00011199303;
                        } else {
                            var7 = 0.004042372;
                        }
                    } else {
                        if (input[2] < 10.0) {
                            var7 = -0.000098346536;
                        } else {
                            var7 = -0.0021051916;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var7 = -0.0008893682;
                        } else {
                            var7 = 0.004337244;
                        }
                    } else {
                        if (input[0] < 47.0) {
                            var7 = 0.004772955;
                        } else {
                            var7 = 0.0021529077;
                        }
                    }
                }
            }
        }
        double var8;
        if (input[3] < 2.06) {
            if (input[3] < 1.94) {
                if (input[3] < 1.85) {
                    if (input[0] < 16.0) {
                        if (input[2] < 9.0) {
                            var8 = -0.00021992631;
                        } else {
                            var8 = 0.001957;
                        }
                    } else {
                        if (input[2] < 10.0) {
                            var8 = -0.00052794575;
                        } else {
                            var8 = -0.0014680208;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var8 = -0.00011613071;
                        } else {
                            var8 = -0.00297474;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var8 = -0.00035965294;
                        } else {
                            var8 = 0.0042302185;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[0] < 73.0) {
                        if (input[3] < 1.99) {
                            var8 = -0.002574801;
                        } else {
                            var8 = -0.0008669657;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var8 = -0.0010132571;
                        } else {
                            var8 = -0.0045179515;
                        }
                    }
                } else {
                    if (input[4] < 2.0) {
                        if (input[4] < 1.0) {
                            var8 = -0.0041018534;
                        } else {
                            var8 = -0.0012835324;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var8 = -0.0013706288;
                        } else {
                            var8 = 0.001711;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 29.0) {
                if (input[1] < 2.0) {
                    if (input[4] < 6.0) {
                        if (input[3] < 3.82) {
                            var8 = -0.00051501155;
                        } else {
                            var8 = 0.00089436193;
                        }
                    } else {
                        if (input[3] < 3.92) {
                            var8 = -0.001089317;
                        } else {
                            var8 = -0.0033607425;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[4] < 1.0) {
                            var8 = -0.00096021773;
                        } else {
                            var8 = 0.00036936768;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var8 = 0.0014075403;
                        } else {
                            var8 = 0.0034742095;
                        }
                    }
                }
            } else {
                if (input[3] < 4.86) {
                    if (input[2] < 4.0) {
                        if (input[1] < 2.0) {
                            var8 = 0.0007607629;
                        } else {
                            var8 = -0.000897812;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var8 = -0.00053418457;
                        } else {
                            var8 = 0.0003065267;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 2.0) {
                            var8 = 0.0022727035;
                        } else {
                            var8 = 0.0002043513;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var8 = 0.0014016646;
                        } else {
                            var8 = 0.006740232;
                        }
                    }
                }
            }
        }
        double var9;
        if (input[3] < 4.4) {
            if (input[0] < 76.0) {
                if (input[1] < 5.0) {
                    if (input[3] < 2.93) {
                        if (input[2] < 4.0) {
                            var9 = -0.00073992595;
                        } else {
                            var9 = -0.000096133954;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var9 = -0.00034584367;
                        } else {
                            var9 = 0.0003674108;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[2] < 10.0) {
                            var9 = -0.0000018959335;
                        } else {
                            var9 = -0.0019172451;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var9 = -0.00079124694;
                        } else {
                            var9 = 0.0025781742;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[2] < 4.0) {
                        if (input[4] < 6.0) {
                            var9 = 0.00043542348;
                        } else {
                            var9 = 0.0023771059;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var9 = 0.00025734908;
                        } else {
                            var9 = -0.0011862193;
                        }
                    }
                } else {
                    if (input[2] < 3.0) {
                        if (input[4] < 2.0) {
                            var9 = 0.00028351185;
                        } else {
                            var9 = -0.0023594226;
                        }
                    } else {
                        if (input[3] < 1.43) {
                            var9 = -0.0017297793;
                        } else {
                            var9 = -0.0003054663;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[1] < 3.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var9 = -0.00027996718;
                        } else {
                            var9 = 0.00007571853;
                        }
                    } else {
                        if (input[0] < 89.0) {
                            var9 = 0.0021348658;
                        } else {
                            var9 = 0.0045210873;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 4.0) {
                            var9 = 0.000009732143;
                        } else {
                            var9 = 0.0021732128;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var9 = 0.0004238309;
                        } else {
                            var9 = -0.002347321;
                        }
                    }
                }
            } else {
                if (input[0] < 23.0) {
                    if (input[1] < 2.0) {
                        if (input[0] < 12.0) {
                            var9 = -0.0016823292;
                        } else {
                            var9 = 0.00052699447;
                        }
                    } else {
                        if (input[3] < 4.41) {
                            var9 = -0.00003448725;
                        } else {
                            var9 = 0.00714682;
                        }
                    }
                } else {
                    if (input[3] < 4.86) {
                        if (input[1] < 4.0) {
                            var9 = -0.0010366067;
                        } else {
                            var9 = 0.0019949;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var9 = 0.0013415848;
                        } else {
                            var9 = 0.0064369277;
                        }
                    }
                }
            }
        }
        double var10;
        if (input[0] < 25.0) {
            if (input[1] < 2.0) {
                if (input[3] < 4.94) {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.91) {
                            var10 = -0.00092556915;
                        } else {
                            var10 = 0.0008652123;
                        }
                    } else {
                        if (input[3] < 3.02) {
                            var10 = -0.00062058837;
                        } else {
                            var10 = -0.0018515338;
                        }
                    }
                } else {
                    if (input[0] < 23.0) {
                        var10 = 0.0039486857;
                    } else {
                        var10 = -0.00029158592;
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var10 = -0.0018312978;
                        } else {
                            var10 = -0.00024814633;
                        }
                    } else {
                        if (input[3] < 2.88) {
                            var10 = 0.0013064322;
                        } else {
                            var10 = -0.00031548442;
                        }
                    }
                } else {
                    if (input[3] < 3.37) {
                        if (input[3] < 1.67) {
                            var10 = -0.0007625774;
                        } else {
                            var10 = 0.0009348166;
                        }
                    } else {
                        if (input[0] < 2.0) {
                            var10 = -0.0019500885;
                        } else {
                            var10 = 0.0027563479;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.66) {
                if (input[2] < 9.0) {
                    if (input[0] < 90.0) {
                        if (input[1] < 5.0) {
                            var10 = -0.00037875035;
                        } else {
                            var10 = 0.0002502054;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var10 = -0.0001991639;
                        } else {
                            var10 = -0.0013557448;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.0) {
                            var10 = -0.0006533741;
                        } else {
                            var10 = 0.00097634335;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var10 = -0.00003831933;
                        } else {
                            var10 = 0.0039571943;
                        }
                    }
                }
            } else {
                if (input[4] < 7.0) {
                    if (input[4] < 2.0) {
                        if (input[4] < 1.0) {
                            var10 = -0.00043501175;
                        } else {
                            var10 = -0.00022666683;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var10 = 0.001385752;
                        } else {
                            var10 = 0.0003454773;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 75.0) {
                            var10 = 0.0020864175;
                        } else {
                            var10 = -0.0031923056;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var10 = 0.0015151058;
                        } else {
                            var10 = 0.0076336265;
                        }
                    }
                }
            }
        }
        double var11;
        if (input[3] < 2.06) {
            if (input[3] < 1.94) {
                if (input[3] < 1.85) {
                    if (input[0] < 16.0) {
                        if (input[2] < 4.0) {
                            var11 = -0.0008552588;
                        } else {
                            var11 = 0.0009782707;
                        }
                    } else {
                        if (input[2] < 10.0) {
                            var11 = -0.00046807775;
                        } else {
                            var11 = -0.0013574437;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var11 = -0.00006945543;
                        } else {
                            var11 = -0.0028104854;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var11 = -0.00030081422;
                        } else {
                            var11 = 0.0040198388;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[0] < 73.0) {
                        if (input[3] < 1.99) {
                            var11 = -0.002431169;
                        } else {
                            var11 = -0.00081304554;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var11 = -0.0009380837;
                        } else {
                            var11 = -0.004246354;
                        }
                    }
                } else {
                    if (input[4] < 2.0) {
                        if (input[4] < 1.0) {
                            var11 = -0.003924964;
                        } else {
                            var11 = -0.0012200356;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var11 = -0.0012948887;
                        } else {
                            var11 = 0.0015654279;
                        }
                    }
                }
            }
        } else {
            if (input[1] < 5.0) {
                if (input[3] < 4.66) {
                    if (input[0] < 75.0) {
                        if (input[1] < 2.0) {
                            var11 = -0.0003252354;
                        } else {
                            var11 = 0.00021385004;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var11 = 0.000077895944;
                        } else {
                            var11 = -0.0011583541;
                        }
                    }
                } else {
                    if (input[4] < 7.0) {
                        if (input[4] < 4.0) {
                            var11 = 0.000083978455;
                        } else {
                            var11 = 0.0011141851;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var11 = 0.000503091;
                        } else {
                            var11 = 0.0047140964;
                        }
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[2] < 10.0) {
                        if (input[0] < 16.0) {
                            var11 = 0.0013366459;
                        } else {
                            var11 = -0.000059495836;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var11 = -0.00039446235;
                        } else {
                            var11 = -0.002889514;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var11 = -0.0008744686;
                        } else {
                            var11 = 0.003929887;
                        }
                    } else {
                        if (input[0] < 47.0) {
                            var11 = 0.0041822796;
                        } else {
                            var11 = 0.0018110753;
                        }
                    }
                }
            }
        }
        double var12;
        if (input[3] < 4.4) {
            if (input[2] < 9.0) {
                if (input[0] < 75.0) {
                    if (input[1] < 5.0) {
                        if (input[3] < 2.93) {
                            var12 = -0.00042492183;
                        } else {
                            var12 = 0.000115067865;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var12 = -0.00035058765;
                        } else {
                            var12 = 0.0009709079;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var12 = 0.0008977147;
                        } else {
                            var12 = -0.00045914456;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var12 = 0.000006749684;
                        } else {
                            var12 = -0.0011806554;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.0) {
                        if (input[0] < 90.0) {
                            var12 = -0.0003844119;
                        } else {
                            var12 = -0.0015164453;
                        }
                    } else {
                        if (input[3] < 4.3) {
                            var12 = 0.0020875204;
                        } else {
                            var12 = -0.0005168124;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.77) {
                            var12 = 0.0011083991;
                        } else {
                            var12 = -0.0017482116;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var12 = 0.0048518633;
                        } else {
                            var12 = 0.0025229037;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[1] < 3.0) {
                    if (input[4] < 4.0) {
                        if (input[2] < 4.0) {
                            var12 = -0.00031034206;
                        } else {
                            var12 = 0.00001951447;
                        }
                    } else {
                        if (input[0] < 89.0) {
                            var12 = 0.0019845797;
                        } else {
                            var12 = 0.00427336;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 4.0) {
                            var12 = -0.0000049372084;
                        } else {
                            var12 = 0.001992533;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var12 = 0.00039756446;
                        } else {
                            var12 = -0.002288879;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 75.0) {
                        if (input[1] < 3.0) {
                            var12 = 0.0024179518;
                        } else {
                            var12 = -0.0002841218;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var12 = -0.0040539484;
                        } else {
                            var12 = -0.0010234654;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var12 = -0.004291318;
                        } else {
                            var12 = 0.00064156734;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var12 = 0.0034510691;
                        } else {
                            var12 = 0.006979828;
                        }
                    }
                }
            }
        }
        double var13;
        if (input[0] < 25.0) {
            if (input[1] < 2.0) {
                if (input[3] < 4.94) {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.91) {
                            var13 = -0.0008336785;
                        } else {
                            var13 = 0.0008343747;
                        }
                    } else {
                        if (input[3] < 3.02) {
                            var13 = -0.00055959035;
                        } else {
                            var13 = -0.0017762346;
                        }
                    }
                } else {
                    if (input[0] < 23.0) {
                        var13 = 0.0036844076;
                    } else {
                        var13 = -0.00028688312;
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var13 = -0.0017384113;
                        } else {
                            var13 = -0.00023908685;
                        }
                    } else {
                        if (input[3] < 2.88) {
                            var13 = 0.0012435985;
                        } else {
                            var13 = -0.00030818096;
                        }
                    }
                } else {
                    if (input[3] < 3.37) {
                        if (input[3] < 1.35) {
                            var13 = -0.0013245845;
                        } else {
                            var13 = 0.0007068249;
                        }
                    } else {
                        if (input[0] < 2.0) {
                            var13 = -0.0018871529;
                        } else {
                            var13 = 0.0025223193;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.66) {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.77) {
                            var13 = 0.000153845;
                        } else {
                            var13 = 0.0013846627;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var13 = -0.00015426506;
                        } else {
                            var13 = -0.001593505;
                        }
                    }
                } else {
                    if (input[3] < 1.45) {
                        if (input[1] < 2.0) {
                            var13 = 0.0006102398;
                        } else {
                            var13 = -0.0014926004;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var13 = -0.000896547;
                        } else {
                            var13 = 0.00032637492;
                        }
                    }
                }
            } else {
                if (input[4] < 7.0) {
                    if (input[4] < 2.0) {
                        if (input[4] < 1.0) {
                            var13 = -0.00042039526;
                        } else {
                            var13 = -0.00022532033;
                        }
                    } else {
                        if (input[0] < 31.0) {
                            var13 = -0.0009342876;
                        } else {
                            var13 = 0.00075080636;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 75.0) {
                            var13 = 0.001918;
                        } else {
                            var13 = -0.00293911;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var13 = 0.001175625;
                        } else {
                            var13 = 0.0068276525;
                        }
                    }
                }
            }
        }
        double var14;
        if (input[0] < 29.0) {
            if (input[1] < 2.0) {
                if (input[3] < 4.94) {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.91) {
                            var14 = -0.00070771045;
                        } else {
                            var14 = 0.00076826377;
                        }
                    } else {
                        if (input[3] < 3.02) {
                            var14 = -0.00059485633;
                        } else {
                            var14 = -0.0016588625;
                        }
                    }
                } else {
                    if (input[0] < 23.0) {
                        var14 = 0.003546238;
                    } else {
                        var14 = -0.00067732536;
                    }
                }
            } else {
                if (input[4] < 5.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.96) {
                            var14 = -0.0017566147;
                        } else {
                            var14 = -0.00022389535;
                        }
                    } else {
                        if (input[3] < 3.84) {
                            var14 = 0.00069935404;
                        } else {
                            var14 = -0.00034017602;
                        }
                    }
                } else {
                    if (input[3] < 4.51) {
                        if (input[3] < 3.37) {
                            var14 = 0.00039717832;
                        } else {
                            var14 = 0.0019568724;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var14 = -0.00018963417;
                        } else {
                            var14 = 0.0064454726;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.86) {
                if (input[2] < 9.0) {
                    if (input[0] < 90.0) {
                        if (input[1] < 5.0) {
                            var14 = -0.000306801;
                        } else {
                            var14 = 0.0002054307;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var14 = -0.00016627078;
                        } else {
                            var14 = -0.0011525847;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 3.38) {
                            var14 = -0.00069702737;
                        } else {
                            var14 = 0.0004037207;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var14 = -0.00014044649;
                        } else {
                            var14 = 0.0034387421;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var14 = -0.00040388803;
                        } else {
                            var14 = 0.0051127495;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var14 = -0.0001939656;
                        } else {
                            var14 = 0.0009717309;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 3.0) {
                            var14 = 0.0033874076;
                        } else {
                            var14 = -0.00040311815;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var14 = 0.0007419745;
                        } else {
                            var14 = 0.006675446;
                        }
                    }
                }
            }
        }
        double var15;
        if (input[3] < 2.06) {
            if (input[3] < 1.94) {
                if (input[3] < 1.85) {
                    if (input[0] < 16.0) {
                        if (input[2] < 4.0) {
                            var15 = -0.0008103251;
                        } else {
                            var15 = 0.0009057436;
                        }
                    } else {
                        if (input[2] < 10.0) {
                            var15 = -0.00039630715;
                        } else {
                            var15 = -0.0012538199;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var15 = -0.000045799094;
                        } else {
                            var15 = -0.0026042939;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var15 = -0.0002332459;
                        } else {
                            var15 = 0.0038063538;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 2.01) {
                        if (input[0] < 73.0) {
                            var15 = -0.00202721;
                        } else {
                            var15 = -0.0037821524;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var15 = -0.0017180004;
                        } else {
                            var15 = -0.000266581;
                        }
                    }
                } else {
                    if (input[4] < 2.0) {
                        if (input[2] < 3.0) {
                            var15 = -0.0006463215;
                        } else {
                            var15 = -0.0033712888;
                        }
                    } else {
                        if (input[3] < 1.97) {
                            var15 = -0.0010666439;
                        } else {
                            var15 = 0.0016461632;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 29.0) {
                if (input[4] < 2.0) {
                    if (input[3] < 2.08) {
                        var15 = -0.003344342;
                    } else {
                        if (input[3] < 2.17) {
                            var15 = 0.0031978549;
                        } else {
                            var15 = -0.00038873148;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.86) {
                            var15 = -0.0006624327;
                        } else {
                            var15 = 0.0024428654;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var15 = 0.0006401177;
                        } else {
                            var15 = 0.0016765259;
                        }
                    }
                }
            } else {
                if (input[3] < 4.86) {
                    if (input[2] < 4.0) {
                        if (input[1] < 2.0) {
                            var15 = 0.0007181309;
                        } else {
                            var15 = -0.00076590857;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var15 = -0.00039844293;
                        } else {
                            var15 = 0.00025431157;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[2] < 4.0) {
                            var15 = 0.0013902128;
                        } else {
                            var15 = -0.000057842182;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var15 = 0.0011269246;
                        } else {
                            var15 = 0.005321896;
                        }
                    }
                }
            }
        }
        double var16;
        if (input[3] < 4.49) {
            if (input[0] < 16.0) {
                if (input[1] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var16 = -0.000013997454;
                        } else {
                            var16 = -0.0010378764;
                        }
                    } else {
                        if (input[3] < 1.42) {
                            var16 = -0.0009160437;
                        } else {
                            var16 = 0.0005576286;
                        }
                    }
                } else {
                    if (input[2] < 9.0) {
                        if (input[4] < 2.0) {
                            var16 = -0.0010581922;
                        } else {
                            var16 = 0.0009308353;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var16 = 0.00042562172;
                        } else {
                            var16 = 0.005124861;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[4] < 4.0) {
                        if (input[3] < 3.08) {
                            var16 = -0.00048774402;
                        } else {
                            var16 = 0.0006728422;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var16 = 0.00058361236;
                        } else {
                            var16 = -0.0014320443;
                        }
                    }
                } else {
                    if (input[3] < 1.45) {
                        if (input[1] < 2.0) {
                            var16 = 0.0005709414;
                        } else {
                            var16 = -0.0013049825;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var16 = -0.0008234462;
                        } else {
                            var16 = 0.00030408485;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[2] < 6.0) {
                    if (input[4] < 4.0) {
                        if (input[2] < 4.0) {
                            var16 = -0.00019397233;
                        } else {
                            var16 = 0.00029280255;
                        }
                    } else {
                        if (input[0] < 93.0) {
                            var16 = 0.002184125;
                        } else {
                            var16 = -0.002203557;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var16 = -0.00027430043;
                        } else {
                            var16 = 0.00072667183;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var16 = 0.001466045;
                        } else {
                            var16 = -0.0024366842;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[2] < 4.0) {
                        if (input[0] < 73.0) {
                            var16 = 0.0020297156;
                        } else {
                            var16 = -0.0024403513;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var16 = -0.003976022;
                        } else {
                            var16 = 0.00087028503;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 33.0) {
                            var16 = 0.0029657865;
                        } else {
                            var16 = -0.001523069;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var16 = 0.0032690507;
                        } else {
                            var16 = 0.006860961;
                        }
                    }
                }
            }
        }
        double var17;
        if (input[1] < 5.0) {
            if (input[3] < 3.8) {
                if (input[0] < 92.0) {
                    if (input[2] < 9.0) {
                        if (input[3] < 2.93) {
                            var17 = -0.00041030356;
                        } else {
                            var17 = 0.000041946667;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var17 = -0.00039757733;
                        } else {
                            var17 = 0.0024818662;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.53) {
                            var17 = 0.0008351921;
                        } else {
                            var17 = -0.0008518239;
                        }
                    } else {
                        if (input[3] < 3.65) {
                            var17 = -0.0010625491;
                        } else {
                            var17 = -0.0037363886;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 4.51) {
                            var17 = -0.00022452591;
                        } else {
                            var17 = -0.00036929265;
                        }
                    } else {
                        if (input[2] < 10.0) {
                            var17 = -0.00013079251;
                        } else {
                            var17 = 0.00006185323;
                        }
                    }
                } else {
                    if (input[2] < 10.0) {
                        if (input[3] < 4.65) {
                            var17 = 0.00015287053;
                        } else {
                            var17 = 0.0007993346;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var17 = 0.0004215117;
                        } else {
                            var17 = 0.0038783834;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 5.0) {
                if (input[2] < 10.0) {
                    if (input[0] < 16.0) {
                        if (input[2] < 7.0) {
                            var17 = 0.0003278901;
                        } else {
                            var17 = 0.0029060368;
                        }
                    } else {
                        if (input[0] < 90.0) {
                            var17 = -0.0000038325315;
                        } else {
                            var17 = -0.000994482;
                        }
                    }
                } else {
                    if (input[0] < 16.0) {
                        if (input[0] < 13.0) {
                            var17 = -0.0016280838;
                        } else {
                            var17 = 0.004140417;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var17 = -0.0011862401;
                        } else {
                            var17 = -0.0029327285;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[3] < 4.82) {
                        if (input[3] < 3.48) {
                            var17 = -0.00021992999;
                        } else {
                            var17 = -0.0016947965;
                        }
                    } else {
                        var17 = 0.0034649135;
                    }
                } else {
                    if (input[0] < 47.0) {
                        if (input[0] < 5.0) {
                            var17 = -0.00031030687;
                        } else {
                            var17 = 0.0034773368;
                        }
                    } else {
                        if (input[3] < 1.53) {
                            var17 = -0.00151192;
                        } else {
                            var17 = 0.0015666381;
                        }
                    }
                }
            }
        }
        double var18;
        if (input[0] < 43.0) {
            if (input[1] < 2.0) {
                if (input[3] < 4.25) {
                    if (input[3] < 4.21) {
                        if (input[4] < 4.0) {
                            var18 = -0.00003834727;
                        } else {
                            var18 = -0.00088802754;
                        }
                    } else {
                        var18 = -0.0053222957;
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 4.77) {
                            var18 = 0.0001656378;
                        } else {
                            var18 = -0.00043117628;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var18 = 0.0016151334;
                        } else {
                            var18 = -0.00049190654;
                        }
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 1.43) {
                            var18 = 0.000980392;
                        } else {
                            var18 = -0.0010820715;
                        }
                    } else {
                        if (input[3] < 1.6) {
                            var18 = -0.00068536896;
                        } else {
                            var18 = 0.0003328792;
                        }
                    }
                } else {
                    if (input[3] < 1.72) {
                        if (input[3] < 1.35) {
                            var18 = -0.0010251784;
                        } else {
                            var18 = 0.00012764854;
                        }
                    } else {
                        if (input[2] < 3.0) {
                            var18 = 0.00031265782;
                        } else {
                            var18 = 0.0014290657;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.86) {
                if (input[2] < 9.0) {
                    if (input[0] < 100.0) {
                        if (input[3] < 4.11) {
                            var18 = -0.00035687047;
                        } else {
                            var18 = 0.00006215529;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var18 = -0.0005449178;
                        } else {
                            var18 = -0.0025987201;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 3.38) {
                            var18 = -0.00080831145;
                        } else {
                            var18 = 0.00048289448;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var18 = -0.00007764395;
                        } else {
                            var18 = 0.0032655988;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var18 = -0.00038422228;
                        } else {
                            var18 = 0.0047840863;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var18 = 0.00026238136;
                        } else {
                            var18 = -0.002010851;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.98) {
                            var18 = -0.0012470663;
                        } else {
                            var18 = 0.003099052;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var18 = 0.00048419632;
                        } else {
                            var18 = 0.005655231;
                        }
                    }
                }
            }
        }
        double var19;
        if (input[1] < 5.0) {
            if (input[3] < 3.8) {
                if (input[0] < 75.0) {
                    if (input[1] < 3.0) {
                        if (input[4] < 4.0) {
                            var19 = -0.00008595569;
                        } else {
                            var19 = -0.0005053961;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var19 = -0.00013576706;
                        } else {
                            var19 = 0.0011678884;
                        }
                    }
                } else {
                    if (input[3] < 3.54) {
                        if (input[1] < 2.0) {
                            var19 = 0.00022962433;
                        } else {
                            var19 = -0.00061015895;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var19 = -0.00030198912;
                        } else {
                            var19 = -0.0030063314;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 4.16) {
                            var19 = -0.00013604872;
                        } else {
                            var19 = -0.00029470577;
                        }
                    } else {
                        if (input[3] < 4.84) {
                            var19 = -0.00010106181;
                        } else {
                            var19 = -0.00022822856;
                        }
                    }
                } else {
                    if (input[2] < 10.0) {
                        if (input[3] < 4.65) {
                            var19 = 0.00013282463;
                        } else {
                            var19 = 0.0007367128;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var19 = 0.00037563464;
                        } else {
                            var19 = 0.003617315;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[2] < 10.0) {
                    if (input[3] < 3.88) {
                        if (input[3] < 3.58) {
                            var19 = 0.00016164532;
                        } else {
                            var19 = 0.0026245588;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var19 = 0.00008182026;
                        } else {
                            var19 = -0.0017813232;
                        }
                    }
                } else {
                    if (input[0] < 16.0) {
                        if (input[0] < 13.0) {
                            var19 = -0.0016448464;
                        } else {
                            var19 = 0.003973039;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var19 = -0.0019967991;
                        } else {
                            var19 = 0.0013010263;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 37.0) {
                        if (input[3] < 4.58) {
                            var19 = -0.00001598256;
                        } else {
                            var19 = 0.0027455648;
                        }
                    } else {
                        if (input[3] < 3.0) {
                            var19 = -0.00041840706;
                        } else {
                            var19 = -0.0024753124;
                        }
                    }
                } else {
                    if (input[3] < 4.27) {
                        if (input[0] < 43.0) {
                            var19 = 0.0029932573;
                        } else {
                            var19 = 0.0004987988;
                        }
                    } else {
                        var19 = 0.00651711;
                    }
                }
            }
        }
        double var20;
        if (input[0] < 19.0) {
            if (input[1] < 3.0) {
                if (input[4] < 7.0) {
                    if (input[2] < 9.0) {
                        if (input[0] < 18.0) {
                            var20 = 0.00019313527;
                        } else {
                            var20 = 0.0016898237;
                        }
                    } else {
                        if (input[0] < 10.0) {
                            var20 = -0.0002599946;
                        } else {
                            var20 = -0.001253748;
                        }
                    }
                } else {
                    if (input[2] < 3.0) {
                        if (input[0] < 4.0) {
                            var20 = 0.0021782906;
                        } else {
                            var20 = -0.000029830422;
                        }
                    } else {
                        if (input[3] < 3.36) {
                            var20 = -0.00087298127;
                        } else {
                            var20 = -0.0032729723;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[3] < 3.97) {
                        if (input[4] < 4.0) {
                            var20 = -0.000054980304;
                        } else {
                            var20 = 0.0016836213;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var20 = -0.0001217796;
                        } else {
                            var20 = -0.002223153;
                        }
                    }
                } else {
                    if (input[3] < 3.76) {
                        if (input[0] < 14.0) {
                            var20 = 0.0014546921;
                        } else {
                            var20 = -0.0005843852;
                        }
                    } else {
                        if (input[2] < 3.0) {
                            var20 = -0.00020503253;
                        } else {
                            var20 = 0.005704857;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.49) {
                if (input[2] < 9.0) {
                    if (input[0] < 75.0) {
                        if (input[1] < 5.0) {
                            var20 = -0.00021495766;
                        } else {
                            var20 = 0.00022307027;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var20 = 0.00021583516;
                        } else {
                            var20 = -0.0007003385;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.0) {
                            var20 = -0.00042214175;
                        } else {
                            var20 = 0.000993309;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var20 = -0.00013535858;
                        } else {
                            var20 = 0.0030613474;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[2] < 6.0) {
                        if (input[4] < 4.0) {
                            var20 = -0.00007640537;
                        } else {
                            var20 = 0.0017439148;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var20 = 0.00024917332;
                        } else {
                            var20 = -0.000912284;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 4.86) {
                            var20 = -0.0019671265;
                        } else {
                            var20 = 0.0021028281;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var20 = -0.0009346627;
                        } else {
                            var20 = 0.0043985946;
                        }
                    }
                }
            }
        }
        double var21;
        if (input[0] < 19.0) {
            if (input[1] < 2.0) {
                if (input[0] < 17.0) {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.78) {
                            var21 = -0.0010958713;
                        } else {
                            var21 = 0.0007010895;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var21 = -0.00011545733;
                        } else {
                            var21 = -0.0014688525;
                        }
                    }
                } else {
                    if (input[3] < 4.65) {
                        if (input[2] < 4.0) {
                            var21 = 0.00038377693;
                        } else {
                            var21 = 0.0010317768;
                        }
                    } else {
                        var21 = 0.0028498769;
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 2.68) {
                        if (input[3] < 2.55) {
                            var21 = 0.0000332577;
                        } else {
                            var21 = 0.0033738508;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var21 = 0.00058637274;
                        } else {
                            var21 = -0.0008181754;
                        }
                    }
                } else {
                    if (input[3] < 1.72) {
                        if (input[2] < 9.0) {
                            var21 = -0.0007573695;
                        } else {
                            var21 = 0.0008402288;
                        }
                    } else {
                        if (input[0] < 2.0) {
                            var21 = -0.00034611227;
                        } else {
                            var21 = 0.0015882433;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.49) {
                if (input[2] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.77) {
                            var21 = 0.00011141007;
                        } else {
                            var21 = 0.0013987065;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var21 = -0.000018025255;
                        } else {
                            var21 = -0.0013460105;
                        }
                    }
                } else {
                    if (input[3] < 1.43) {
                        if (input[1] < 2.0) {
                            var21 = 0.00063977455;
                        } else {
                            var21 = -0.0012256549;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var21 = -0.0009481189;
                        } else {
                            var21 = 0.00025174566;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[2] < 6.0) {
                        if (input[4] < 4.0) {
                            var21 = -0.0000726401;
                        } else {
                            var21 = 0.0016588413;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var21 = 0.00023687985;
                        } else {
                            var21 = -0.00086778693;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[2] < 4.0) {
                            var21 = 0.0011824801;
                        } else {
                            var21 = -0.0022913467;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var21 = -0.0008908533;
                        } else {
                            var21 = 0.0041871127;
                        }
                    }
                }
            }
        }
        double var22;
        if (input[1] < 5.0) {
            if (input[3] < 3.8) {
                if (input[0] < 92.0) {
                    if (input[2] < 9.0) {
                        if (input[1] < 4.0) {
                            var22 = -0.00010865884;
                        } else {
                            var22 = -0.0005812159;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var22 = -0.0003513853;
                        } else {
                            var22 = 0.0022425016;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[4] < 2.0) {
                            var22 = -0.00038166888;
                        } else {
                            var22 = 0.0009390914;
                        }
                    } else {
                        if (input[3] < 3.65) {
                            var22 = -0.0009127684;
                        } else {
                            var22 = -0.0033959895;
                        }
                    }
                }
            } else {
                if (input[2] < 10.0) {
                    if (input[0] < 39.0) {
                        if (input[4] < 4.0) {
                            var22 = -0.000048151047;
                        } else {
                            var22 = 0.0008007952;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var22 = 0.00019355396;
                        } else {
                            var22 = -0.00066488725;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[0] < 18.0) {
                            var22 = -0.001333426;
                        } else {
                            var22 = 0.0004087639;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var22 = 0.00030644282;
                        } else {
                            var22 = 0.0057344404;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 5.0) {
                if (input[2] < 10.0) {
                    if (input[0] < 16.0) {
                        if (input[2] < 7.0) {
                            var22 = 0.00027446033;
                        } else {
                            var22 = 0.0027220817;
                        }
                    } else {
                        if (input[0] < 93.0) {
                            var22 = -0.00001680726;
                        } else {
                            var22 = -0.0010513888;
                        }
                    }
                } else {
                    if (input[4] < 3.0) {
                        if (input[3] < 2.08) {
                            var22 = -0.00216246;
                        } else {
                            var22 = 0.00022100168;
                        }
                    } else {
                        if (input[0] < 15.0) {
                            var22 = -0.0007832368;
                        } else {
                            var22 = -0.002671957;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[3] < 4.82) {
                        if (input[3] < 3.48) {
                            var22 = -0.00017480712;
                        } else {
                            var22 = -0.0015274882;
                        }
                    } else {
                        var22 = 0.003208556;
                    }
                } else {
                    if (input[3] < 1.55) {
                        if (input[3] < 1.21) {
                            var22 = -0.0032103464;
                        } else {
                            var22 = 0.00015552113;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var22 = 0.0047384305;
                        } else {
                            var22 = 0.0017696684;
                        }
                    }
                }
            }
        }
        double var23;
        if (input[0] < 75.0) {
            if (input[4] < 1.0) {
                if (input[3] < 1.43) {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.19) {
                            var23 = -0.0007800443;
                        } else {
                            var23 = 0.0008257394;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var23 = 0.00025709494;
                        } else {
                            var23 = 0.0026425668;
                        }
                    }
                } else {
                    if (input[3] < 2.22) {
                        if (input[1] < 4.0) {
                            var23 = 0.000066254135;
                        } else {
                            var23 = -0.0038481054;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var23 = 0.0003933783;
                        } else {
                            var23 = -0.0004965903;
                        }
                    }
                }
            } else {
                if (input[3] < 1.62) {
                    if (input[1] < 2.0) {
                        if (input[3] < 1.3) {
                            var23 = 0.00071224524;
                        } else {
                            var23 = -0.0001506091;
                        }
                    } else {
                        if (input[3] < 1.16) {
                            var23 = -0.0013003316;
                        } else {
                            var23 = -0.00031350966;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.03) {
                            var23 = -0.0019251698;
                        } else {
                            var23 = -0.0001952629;
                        }
                    } else {
                        if (input[3] < 1.94) {
                            var23 = 0.0015000001;
                        } else {
                            var23 = 0.00037080125;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 3.8) {
                if (input[3] < 3.39) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.85) {
                            var23 = -0.00009672453;
                        } else {
                            var23 = 0.001634522;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var23 = -0.0007579763;
                        } else {
                            var23 = -0.00008723237;
                        }
                    }
                } else {
                    if (input[4] < 3.0) {
                        if (input[2] < 7.0) {
                            var23 = 0.00023920997;
                        } else {
                            var23 = -0.00041423706;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var23 = -0.00048233272;
                        } else {
                            var23 = -0.0026720595;
                        }
                    }
                }
            } else {
                if (input[2] < 5.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var23 = -0.00012652695;
                        } else {
                            var23 = 0.0007966691;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var23 = 0.0012175044;
                        } else {
                            var23 = -0.0031606127;
                        }
                    }
                } else {
                    if (input[4] < 5.0) {
                        if (input[3] < 3.83) {
                            var23 = 0.004423213;
                        } else {
                            var23 = 0.00007054608;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var23 = -0.0012064188;
                        } else {
                            var23 = 0.0017767994;
                        }
                    }
                }
            }
        }
        double var24;
        if (input[0] < 43.0) {
            if (input[1] < 2.0) {
                if (input[3] < 4.38) {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.91) {
                            var24 = -0.0006454668;
                        } else {
                            var24 = 0.0010025556;
                        }
                    } else {
                        if (input[3] < 3.02) {
                            var24 = -0.0002940794;
                        } else {
                            var24 = -0.0019781764;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[2] < 4.0) {
                            var24 = -0.0005870479;
                        } else {
                            var24 = 0.00013181269;
                        }
                    } else {
                        if (input[0] < 39.0) {
                            var24 = 0.001246413;
                        } else {
                            var24 = -0.0020972253;
                        }
                    }
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.96) {
                            var24 = -0.0014157379;
                        } else {
                            var24 = -0.00009542371;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var24 = -0.001577752;
                        } else {
                            var24 = 0.00020409685;
                        }
                    }
                } else {
                    if (input[3] < 1.72) {
                        if (input[1] < 3.0) {
                            var24 = -0.0012765157;
                        } else {
                            var24 = -0.000057721743;
                        }
                    } else {
                        if (input[2] < 3.0) {
                            var24 = 0.00026805067;
                        } else {
                            var24 = 0.0012110334;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.86) {
                if (input[2] < 9.0) {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.03) {
                            var24 = -0.00052632473;
                        } else {
                            var24 = -0.000023617968;
                        }
                    } else {
                        if (input[3] < 2.71) {
                            var24 = -0.000063691885;
                        } else {
                            var24 = -0.00092165265;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 3.38) {
                            var24 = -0.00069728045;
                        } else {
                            var24 = 0.00042897463;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var24 = -0.001377242;
                        } else {
                            var24 = 0.0017507019;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var24 = -0.00040013195;
                        } else {
                            var24 = 0.004493311;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var24 = 0.00021712626;
                        } else {
                            var24 = -0.0019677307;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.98) {
                            var24 = -0.001104325;
                        } else {
                            var24 = 0.002966094;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var24 = 0.00043614706;
                        } else {
                            var24 = 0.0047846385;
                        }
                    }
                }
            }
        }
        double var25;
        if (input[1] < 5.0) {
            if (input[3] < 3.8) {
                if (input[2] < 9.0) {
                    if (input[1] < 4.0) {
                        if (input[4] < 4.0) {
                            var25 = 0.00006542068;
                        } else {
                            var25 = -0.0003438621;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var25 = 0.000023666229;
                        } else {
                            var25 = -0.001146213;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[4] < 5.0) {
                            var25 = -0.000060838036;
                        } else {
                            var25 = -0.00096410513;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var25 = -0.0011378288;
                        } else {
                            var25 = 0.004332522;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[3] < 4.51) {
                        if (input[1] < 2.0) {
                            var25 = -0.00023595941;
                        } else {
                            var25 = -0.00009848255;
                        }
                    } else {
                        if (input[0] < 23.0) {
                            var25 = -0.0001456582;
                        } else {
                            var25 = -0.00032396562;
                        }
                    }
                } else {
                    if (input[3] < 4.65) {
                        if (input[0] < 17.0) {
                            var25 = 0.00074993947;
                        } else {
                            var25 = -0.000007663846;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var25 = 0.00045195743;
                        } else {
                            var25 = 0.0019574214;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[3] < 3.88) {
                    if (input[3] < 3.58) {
                        if (input[4] < 4.0) {
                            var25 = -0.00036367195;
                        } else {
                            var25 = 0.0007374046;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var25 = 0.0004988709;
                        } else {
                            var25 = 0.0043772715;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 4.22) {
                            var25 = -0.0004202763;
                        } else {
                            var25 = 0.0003155027;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var25 = 0.000961114;
                        } else {
                            var25 = -0.0031098842;
                        }
                    }
                }
            } else {
                if (input[3] < 4.03) {
                    if (input[0] < 43.0) {
                        if (input[2] < 4.0) {
                            var25 = -0.000055574452;
                        } else {
                            var25 = 0.0024621156;
                        }
                    } else {
                        if (input[3] < 3.33) {
                            var25 = 0.00012095455;
                        } else {
                            var25 = -0.001641787;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var25 = -0.001077448;
                        } else {
                            var25 = 0.0025331578;
                        }
                    } else {
                        if (input[3] < 4.27) {
                            var25 = 0.002430671;
                        } else {
                            var25 = 0.0057157963;
                        }
                    }
                }
            }
        }
        double var26;
        if (input[0] < 75.0) {
            if (input[4] < 1.0) {
                if (input[3] < 1.45) {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.19) {
                            var26 = -0.00070135796;
                        } else {
                            var26 = 0.0008266832;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var26 = 0.00029908793;
                        } else {
                            var26 = 0.002357155;
                        }
                    }
                } else {
                    if (input[3] < 2.22) {
                        if (input[1] < 4.0) {
                            var26 = 0.00009456918;
                        } else {
                            var26 = -0.003751209;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var26 = 0.00037390337;
                        } else {
                            var26 = -0.00044861148;
                        }
                    }
                }
            } else {
                if (input[3] < 1.62) {
                    if (input[1] < 2.0) {
                        if (input[3] < 1.3) {
                            var26 = 0.0007128331;
                        } else {
                            var26 = -0.00011216579;
                        }
                    } else {
                        if (input[3] < 1.16) {
                            var26 = -0.0012073255;
                        } else {
                            var26 = -0.00028056838;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.03) {
                            var26 = -0.0017970151;
                        } else {
                            var26 = -0.00017239914;
                        }
                    } else {
                        if (input[3] < 1.94) {
                            var26 = 0.0014274734;
                        } else {
                            var26 = 0.00032944718;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 3.8) {
                if (input[3] < 3.39) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.85) {
                            var26 = -0.00006923411;
                        } else {
                            var26 = 0.0015934695;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var26 = -0.00069189677;
                        } else {
                            var26 = -0.00007461841;
                        }
                    }
                } else {
                    if (input[4] < 3.0) {
                        if (input[2] < 7.0) {
                            var26 = 0.00023441776;
                        } else {
                            var26 = -0.0004037877;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var26 = -0.00042995973;
                        } else {
                            var26 = -0.0025096065;
                        }
                    }
                }
            } else {
                if (input[2] < 5.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var26 = -0.00011430562;
                        } else {
                            var26 = 0.0007545019;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var26 = 0.0011516165;
                        } else {
                            var26 = -0.003010215;
                        }
                    }
                } else {
                    if (input[3] < 3.83) {
                        var26 = 0.0042037903;
                    } else {
                        if (input[4] < 5.0) {
                            var26 = 0.000054887765;
                        } else {
                            var26 = 0.0010701882;
                        }
                    }
                }
            }
        }
        double var27;
        if (input[0] < 29.0) {
            if (input[1] < 2.0) {
                if (input[0] < 1.0) {
                    if (input[4] < 1.0) {
                        var27 = 0.000037381054;
                    } else {
                        if (input[4] < 6.0) {
                            var27 = -0.0021836534;
                        } else {
                            var27 = -0.00056394935;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[0] < 2.0) {
                            var27 = 0.001717894;
                        } else {
                            var27 = 0.00007015236;
                        }
                    } else {
                        if (input[3] < 4.81) {
                            var27 = -0.0007569687;
                        } else {
                            var27 = 0.002885604;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 1.88) {
                        if (input[0] < 13.0) {
                            var27 = 0.0018829134;
                        } else {
                            var27 = -0.000675342;
                        }
                    } else {
                        if (input[3] < 2.55) {
                            var27 = -0.005362371;
                        } else {
                            var27 = -0.00023421865;
                        }
                    }
                } else {
                    if (input[3] < 1.34) {
                        if (input[2] < 7.0) {
                            var27 = -0.0004396198;
                        } else {
                            var27 = -0.0017852664;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var27 = 0.00046666447;
                        } else {
                            var27 = 0.0011802244;
                        }
                    }
                }
            }
        } else {
            if (input[3] < 4.86) {
                if (input[2] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 5.0) {
                            var27 = 0.000020234593;
                        } else {
                            var27 = 0.001186102;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var27 = -0.00009309402;
                        } else {
                            var27 = -0.0012420564;
                        }
                    }
                } else {
                    if (input[3] < 1.45) {
                        if (input[1] < 2.0) {
                            var27 = 0.00063276495;
                        } else {
                            var27 = -0.0009503925;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var27 = -0.0006588158;
                        } else {
                            var27 = 0.00013514789;
                        }
                    }
                }
            } else {
                if (input[4] < 6.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var27 = -0.00038670897;
                        } else {
                            var27 = 0.004294938;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var27 = -0.00050677086;
                        } else {
                            var27 = 0.0005177358;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 3.0) {
                            var27 = 0.0028247356;
                        } else {
                            var27 = -0.0003606987;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var27 = 0.0003832102;
                        } else {
                            var27 = 0.004656035;
                        }
                    }
                }
            }
        }
        double var28;
        if (input[1] < 5.0) {
            if (input[2] < 9.0) {
                if (input[1] < 4.0) {
                    if (input[3] < 2.93) {
                        if (input[2] < 4.0) {
                            var28 = -0.0005128117;
                        } else {
                            var28 = -0.00003131927;
                        }
                    } else {
                        if (input[3] < 3.02) {
                            var28 = 0.0016516204;
                        } else {
                            var28 = 0.00007171249;
                        }
                    }
                } else {
                    if (input[3] < 4.65) {
                        if (input[2] < 4.0) {
                            var28 = -0.00019996175;
                        } else {
                            var28 = -0.00082514586;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var28 = 0.0000002945623;
                        } else {
                            var28 = 0.0035985082;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.0) {
                        if (input[4] < 5.0) {
                            var28 = -0.000060536604;
                        } else {
                            var28 = -0.0008492634;
                        }
                    } else {
                        if (input[3] < 4.29) {
                            var28 = 0.0018641353;
                        } else {
                            var28 = 0.00016475035;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[3] < 2.74) {
                            var28 = 0.002508379;
                        } else {
                            var28 = -0.0007956071;
                        }
                    } else {
                        if (input[3] < 1.04) {
                            var28 = 0.0011413008;
                        } else {
                            var28 = 0.005578368;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 5.0) {
                if (input[2] < 10.0) {
                    if (input[0] < 16.0) {
                        if (input[2] < 7.0) {
                            var28 = 0.00022158232;
                        } else {
                            var28 = 0.0025234339;
                        }
                    } else {
                        if (input[0] < 93.0) {
                            var28 = -0.000010723116;
                        } else {
                            var28 = -0.00096444896;
                        }
                    }
                } else {
                    if (input[4] < 3.0) {
                        if (input[3] < 2.08) {
                            var28 = -0.0019135637;
                        } else {
                            var28 = 0.00021611921;
                        }
                    } else {
                        if (input[0] < 15.0) {
                            var28 = -0.00084761385;
                        } else {
                            var28 = -0.0025718375;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[3] < 4.82) {
                        if (input[3] < 3.48) {
                            var28 = -0.00016930408;
                        } else {
                            var28 = -0.0014134996;
                        }
                    } else {
                        var28 = 0.0029430008;
                    }
                } else {
                    if (input[0] < 47.0) {
                        if (input[0] < 5.0) {
                            var28 = -0.0006567606;
                        } else {
                            var28 = 0.002709131;
                        }
                    } else {
                        if (input[3] < 1.53) {
                            var28 = -0.0013514454;
                        } else {
                            var28 = 0.0011847274;
                        }
                    }
                }
            }
        }
        return subroutine1(input) + var0 + var1 + var2 + var3 + var4 + var5 + var6 + var7 + var8 + var9 + var10 + var11 + var12 + var13 + var14 + var15 + var16 + var17 + var18 + var19 + var20 + var21 + var22 + var23 + var24 + var25 + var26 + var27 + var28;
    }
    public static double subroutine1(double[] input) {
        double var0;
        if (input[3] < 3.18) {
            if (input[3] < 1.77) {
                if (input[0] < 30.0) {
                    if (input[3] < 1.53) {
                        if (input[2] < 4.0) {
                            var0 = -0.0031144475;
                        } else {
                            var0 = -0.0015198136;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var0 = -0.0021213142;
                        } else {
                            var0 = 0.0023757638;
                        }
                    }
                } else {
                    if (input[3] < 1.18) {
                        if (input[1] < 2.0) {
                            var0 = -0.00094205997;
                        } else {
                            var0 = -0.0041912305;
                        }
                    } else {
                        if (input[2] < 6.0) {
                            var0 = -0.0031705655;
                        } else {
                            var0 = -0.0019385975;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 24.0) {
                        if (input[1] < 2.0) {
                            var0 = -0.0034065235;
                        } else {
                            var0 = 0.00069225125;
                        }
                    } else {
                        if (input[0] < 89.0) {
                            var0 = -0.0022794737;
                        } else {
                            var0 = -0.004807481;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.22) {
                            var0 = -0.0072342055;
                        } else {
                            var0 = -0.0026769065;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var0 = -0.0027047533;
                        } else {
                            var0 = 0.0016057175;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 3.2) {
                            var0 = -0.00042970479;
                        } else {
                            var0 = -0.0014742725;
                        }
                    } else {
                        if (input[3] < 3.32) {
                            var0 = -0.0010459911;
                        } else {
                            var0 = -0.0004910943;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 41.0) {
                            var0 = 0.0030361556;
                        } else {
                            var0 = 0.001104636;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var0 = 0.0016209735;
                        } else {
                            var0 = -0.0010780444;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.43) {
                        if (input[0] < 39.0) {
                            var0 = 0.0019653782;
                        } else {
                            var0 = -0.0013013416;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var0 = 0.003982749;
                        } else {
                            var0 = 0.008996456;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 45.0) {
                            var0 = 0.0028048635;
                        } else {
                            var0 = -0.0036752638;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var0 = 0.003913251;
                        } else {
                            var0 = 0.011307924;
                        }
                    }
                }
            }
        }
        double var1;
        if (input[3] < 3.54) {
            if (input[3] < 2.46) {
                if (input[2] < 9.0) {
                    if (input[0] < 78.0) {
                        if (input[1] < 5.0) {
                            var1 = -0.0020715622;
                        } else {
                            var1 = -0.00036151047;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var1 = -0.0017897197;
                        } else {
                            var1 = -0.00363607;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[0] < 52.0) {
                            var1 = -0.001089556;
                        } else {
                            var1 = -0.0035024234;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var1 = -0.0025012125;
                        } else {
                            var1 = 0.005980758;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 50.0) {
                        if (input[1] < 2.0) {
                            var1 = -0.0018157348;
                        } else {
                            var1 = 0.0010290294;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var1 = -0.0017243391;
                        } else {
                            var1 = 0.0005587086;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.62) {
                            var1 = 0.0047198464;
                        } else {
                            var1 = -0.0016242372;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var1 = -0.000026535563;
                        } else {
                            var1 = 0.007359825;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var1 = -0.0013661518;
                    } else {
                        var1 = -0.00047209646;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var1 = 0.0036833037;
                        } else {
                            var1 = 0.0012099547;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var1 = -0.00089708733;
                        } else {
                            var1 = 0.0013603488;
                        }
                    }
                }
            } else {
                if (input[3] < 4.63) {
                    if (input[2] < 4.0) {
                        if (input[0] < 29.0) {
                            var1 = 0.0031607186;
                        } else {
                            var1 = -0.0024857696;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var1 = -0.0035398826;
                        } else {
                            var1 = 0.0057244957;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 3.0) {
                            var1 = 0.0068943324;
                        } else {
                            var1 = 0.00182106;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var1 = 0.004545461;
                        } else {
                            var1 = 0.013603041;
                        }
                    }
                }
            }
        }
        double var2;
        if (input[3] < 3.54) {
            if (input[3] < 2.06) {
                if (input[0] < 40.0) {
                    if (input[2] < 8.0) {
                        if (input[1] < 3.0) {
                            var2 = -0.002843616;
                        } else {
                            var2 = -0.0011617722;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var2 = -0.0024368262;
                        } else {
                            var2 = 0.0024674626;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[4] < 1.0) {
                            var2 = -0.003698123;
                        } else {
                            var2 = -0.0026175608;
                        }
                    } else {
                        if (input[3] < 1.48) {
                            var2 = -0.002865286;
                        } else {
                            var2 = 0.00008759213;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 52.0) {
                        if (input[1] < 2.0) {
                            var2 = -0.0018551222;
                        } else {
                            var2 = 0.00068968127;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var2 = -0.001913451;
                        } else {
                            var2 = 0.00029392674;
                        }
                    }
                } else {
                    if (input[4] < 5.0) {
                        if (input[4] < 2.0) {
                            var2 = -0.0017540128;
                        } else {
                            var2 = 0.00076234824;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var2 = 0.00027986884;
                        } else {
                            var2 = 0.006817199;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var2 = -0.0012983703;
                    } else {
                        var2 = -0.00044867318;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var2 = 0.0035039838;
                        } else {
                            var2 = 0.0011502963;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var2 = -0.0006344983;
                        } else {
                            var2 = 0.0014008173;
                        }
                    }
                }
            } else {
                if (input[3] < 4.53) {
                    if (input[2] < 9.0) {
                        if (input[0] < 39.0) {
                            var2 = 0.003106046;
                        } else {
                            var2 = -0.00049696903;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var2 = 0.001882431;
                        } else {
                            var2 = 0.011540357;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 3.0) {
                            var2 = 0.00625439;
                        } else {
                            var2 = 0.0013940491;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var2 = 0.00021582544;
                        } else {
                            var2 = 0.011828709;
                        }
                    }
                }
            }
        }
        double var3;
        if (input[3] < 3.36) {
            if (input[3] < 1.77) {
                if (input[3] < 1.19) {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var3 = -0.0012524285;
                        } else {
                            var3 = -0.0003808751;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var3 = -0.0020251167;
                        } else {
                            var3 = -0.0047427746;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[0] < 4.0) {
                            var3 = 0.00003094435;
                        } else {
                            var3 = -0.0028340872;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var3 = -0.003116214;
                        } else {
                            var3 = -0.0001146396;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 2.22) {
                        if (input[1] < 3.0) {
                            var3 = -0.0011673023;
                        } else {
                            var3 = -0.0072701285;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var3 = -0.00074433925;
                        } else {
                            var3 = -0.002610746;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 2.88) {
                            var3 = -0.0019298937;
                        } else {
                            var3 = -0.0003034264;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var3 = 0.000028867076;
                        } else {
                            var3 = 0.004160438;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var3 = -0.0012392106;
                    } else {
                        if (input[3] < 3.42) {
                            var3 = -0.0002591946;
                        } else {
                            var3 = -0.00042428053;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.22) {
                            var3 = 0.0025846015;
                        } else {
                            var3 = 0.0009452578;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var3 = 0.0013627062;
                        } else {
                            var3 = -0.0007312804;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[3] < 4.65) {
                        if (input[0] < 59.0) {
                            var3 = 0.002479469;
                        } else {
                            var3 = -0.0013771387;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var3 = 0.004681653;
                        } else {
                            var3 = 0.009519859;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[3] < 3.92) {
                            var3 = 0.007283295;
                        } else {
                            var3 = 0.000034875648;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var3 = 0.00033268853;
                        } else {
                            var3 = 0.012503566;
                        }
                    }
                }
            }
        }
        double var4;
        if (input[3] < 3.54) {
            if (input[3] < 2.46) {
                if (input[2] < 9.0) {
                    if (input[0] < 78.0) {
                        if (input[2] < 4.0) {
                            var4 = -0.002221818;
                        } else {
                            var4 = -0.00094009377;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var4 = -0.0014676305;
                        } else {
                            var4 = -0.0032881044;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[0] < 52.0) {
                            var4 = -0.0009548016;
                        } else {
                            var4 = -0.0031891272;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var4 = -0.002230665;
                        } else {
                            var4 = 0.005566639;
                        }
                    }
                }
            } else {
                if (input[0] < 74.0) {
                    if (input[4] < 2.0) {
                        if (input[4] < 1.0) {
                            var4 = -0.0019853415;
                        } else {
                            var4 = -0.00043878108;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var4 = 0.0004870862;
                        } else {
                            var4 = 0.0036478683;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 2.0) {
                            var4 = 0.0001921642;
                        } else {
                            var4 = -0.0043678954;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var4 = -0.0012047008;
                        } else {
                            var4 = 0.00025161263;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var4 = -0.001172466;
                    } else {
                        var4 = -0.000405352;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var4 = 0.0032075588;
                        } else {
                            var4 = 0.0010344437;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var4 = -0.00084822107;
                        } else {
                            var4 = 0.0012057875;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.43) {
                        if (input[1] < 2.0) {
                            var4 = -0.002212329;
                        } else {
                            var4 = 0.0014567799;
                        }
                    } else {
                        if (input[3] < 4.96) {
                            var4 = 0.0034957607;
                        } else {
                            var4 = 0.01039834;
                        }
                    }
                } else {
                    if (input[2] < 3.0) {
                        if (input[3] < 4.44) {
                            var4 = -0.0035466347;
                        } else {
                            var4 = 0.0033123088;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var4 = 0.0024823674;
                        } else {
                            var4 = 0.010771787;
                        }
                    }
                }
            }
        }
        double var5;
        if (input[3] < 3.59) {
            if (input[3] < 2.06) {
                if (input[0] < 40.0) {
                    if (input[2] < 8.0) {
                        if (input[1] < 3.0) {
                            var5 = -0.0025133137;
                        } else {
                            var5 = -0.000940305;
                        }
                    } else {
                        if (input[3] < 1.49) {
                            var5 = -0.0014053046;
                        } else {
                            var5 = 0.0029507997;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[4] < 1.0) {
                            var5 = -0.0032831244;
                        } else {
                            var5 = -0.0023052457;
                        }
                    } else {
                        if (input[3] < 1.48) {
                            var5 = -0.002539671;
                        } else {
                            var5 = 0.00017866479;
                        }
                    }
                }
            } else {
                if (input[0] < 73.0) {
                    if (input[1] < 5.0) {
                        if (input[1] < 2.0) {
                            var5 = -0.0014072309;
                        } else {
                            var5 = 0.0003039619;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var5 = -0.00041260428;
                        } else {
                            var5 = 0.0049458123;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 2.0) {
                            var5 = 0.00008129537;
                        } else {
                            var5 = -0.003662421;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var5 = -0.0014629689;
                        } else {
                            var5 = 0.00020627583;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var5 = -0.0011142287;
                    } else {
                        var5 = -0.00038480092;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.85) {
                            var5 = 0.0037535005;
                        } else {
                            var5 = 0.0011976367;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var5 = -0.0005946722;
                        } else {
                            var5 = 0.0012432326;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[3] < 4.43) {
                        if (input[2] < 4.0) {
                            var5 = -0.0016928616;
                        } else {
                            var5 = 0.0017985881;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var5 = 0.003800539;
                        } else {
                            var5 = 0.00801717;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[3] < 3.93) {
                            var5 = 0.0073072077;
                        } else {
                            var5 = 0.0012029748;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var5 = 0.002372059;
                        } else {
                            var5 = 0.017575921;
                        }
                    }
                }
            }
        }
        double var6;
        if (input[3] < 3.2) {
            if (input[2] < 4.0) {
                if (input[0] < 19.0) {
                    if (input[3] < 2.14) {
                        if (input[1] < 2.0) {
                            var6 = -0.0033108795;
                        } else {
                            var6 = -0.0013634682;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var6 = -0.003973645;
                        } else {
                            var6 = 0.0015696805;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.85) {
                            var6 = -0.0013995393;
                        } else {
                            var6 = 0.0014919984;
                        }
                    } else {
                        if (input[0] < 82.0) {
                            var6 = -0.002129315;
                        } else {
                            var6 = -0.0034609959;
                        }
                    }
                }
            } else {
                if (input[3] < 1.45) {
                    if (input[3] < 1.18) {
                        if (input[1] < 2.0) {
                            var6 = -0.00019754186;
                        } else {
                            var6 = -0.004584596;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var6 = -0.0029661797;
                        } else {
                            var6 = -0.0006978556;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[1] < 4.0) {
                            var6 = -0.002114631;
                        } else {
                            var6 = -0.005243674;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var6 = -0.0025522723;
                        } else {
                            var6 = 0.0013236834;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 3.36) {
                            var6 = -0.0014772983;
                        } else {
                            var6 = -0.0010652529;
                        }
                    } else {
                        if (input[3] < 3.32) {
                            var6 = -0.0009255644;
                        } else {
                            var6 = -0.00034282688;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.19) {
                            var6 = 0.0015229821;
                        } else {
                            var6 = 0.0010195366;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var6 = 0.002202889;
                        } else {
                            var6 = -0.0010724346;
                        }
                    }
                }
            } else {
                if (input[0] < 60.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.53) {
                            var6 = -0.0022730806;
                        } else {
                            var6 = 0.004248191;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var6 = 0.0037240384;
                        } else {
                            var6 = 0.008401326;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 2.0) {
                            var6 = 0.0027555206;
                        } else {
                            var6 = -0.004881228;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var6 = -0.00046266124;
                        } else {
                            var6 = 0.0036558004;
                        }
                    }
                }
            }
        }
        double var7;
        if (input[3] < 3.54) {
            if (input[2] < 9.0) {
                if (input[0] < 30.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.85) {
                            var7 = -0.0027083855;
                        } else {
                            var7 = -0.00058435666;
                        }
                    } else {
                        if (input[3] < 1.51) {
                            var7 = -0.0018115813;
                        } else {
                            var7 = 0.0014773271;
                        }
                    }
                } else {
                    if (input[0] < 78.0) {
                        if (input[1] < 5.0) {
                            var7 = -0.0014385609;
                        } else {
                            var7 = -0.000051663344;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var7 = -0.0009384756;
                        } else {
                            var7 = -0.002559705;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 1.68) {
                        if (input[1] < 2.0) {
                            var7 = -0.0010036819;
                        } else {
                            var7 = -0.003683198;
                        }
                    } else {
                        if (input[0] < 57.0) {
                            var7 = 0.00019588145;
                        } else {
                            var7 = -0.0016323397;
                        }
                    }
                } else {
                    if (input[4] < 3.0) {
                        if (input[0] < 16.0) {
                            var7 = 0.0024833647;
                        } else {
                            var7 = -0.0021278725;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var7 = 0.009465982;
                        } else {
                            var7 = 0.0053577623;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var7 = -0.0010067043;
                    } else {
                        var7 = -0.00034802078;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var7 = 0.002850998;
                        } else {
                            var7 = 0.000851479;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var7 = -0.00080392073;
                        } else {
                            var7 = 0.001079803;
                        }
                    }
                }
            } else {
                if (input[0] < 25.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.25) {
                            var7 = -0.0032614195;
                        } else {
                            var7 = 0.003037782;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var7 = 0.00412132;
                        } else {
                            var7 = 0.00928055;
                        }
                    }
                } else {
                    if (input[3] < 4.63) {
                        if (input[2] < 4.0) {
                            var7 = -0.0021383243;
                        } else {
                            var7 = 0.0019546403;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var7 = 0.0026877746;
                        } else {
                            var7 = 0.007548656;
                        }
                    }
                }
            }
        }
        double var8;
        if (input[3] < 2.46) {
            if (input[2] < 4.0) {
                if (input[0] < 9.0) {
                    if (input[4] < 4.0) {
                        if (input[3] < 1.97) {
                            var8 = -0.0025637476;
                        } else {
                            var8 = -0.0010169186;
                        }
                    } else {
                        if (input[3] < 1.85) {
                            var8 = -0.0016577662;
                        } else {
                            var8 = 0.0018409074;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[0] < 61.0) {
                            var8 = -0.0016486744;
                        } else {
                            var8 = -0.00049744867;
                        }
                    } else {
                        if (input[3] < 1.31) {
                            var8 = -0.0014479655;
                        } else {
                            var8 = -0.0028799658;
                        }
                    }
                }
            } else {
                if (input[3] < 1.35) {
                    if (input[1] < 2.0) {
                        if (input[0] < 47.0) {
                            var8 = -0.00066510506;
                        } else {
                            var8 = -0.000028160066;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var8 = -0.005042979;
                        } else {
                            var8 = -0.0021602039;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[1] < 4.0) {
                            var8 = -0.0015960907;
                        } else {
                            var8 = -0.0068826713;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var8 = -0.0018094758;
                        } else {
                            var8 = 0.0016685583;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var8 = -0.0022940845;
                        } else {
                            var8 = -0.0010236733;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var8 = -0.0002009025;
                        } else {
                            var8 = -0.0011286754;
                        }
                    }
                } else {
                    if (input[0] < 92.0) {
                        if (input[3] < 2.68) {
                            var8 = 0.0024895866;
                        } else {
                            var8 = 0.0004605858;
                        }
                    } else {
                        if (input[3] < 3.62) {
                            var8 = -0.0031903293;
                        } else {
                            var8 = 0.0009960885;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[3] < 4.41) {
                        if (input[0] < 25.0) {
                            var8 = 0.0023130646;
                        } else {
                            var8 = -0.00070348586;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var8 = 0.0029573452;
                        } else {
                            var8 = 0.007086932;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 43.0) {
                            var8 = 0.00203812;
                        } else {
                            var8 = -0.003336828;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var8 = 0.003215864;
                        } else {
                            var8 = 0.009303674;
                        }
                    }
                }
            }
        }
        double var9;
        if (input[3] < 2.46) {
            if (input[2] < 9.0) {
                if (input[0] < 40.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 1.55) {
                            var9 = -0.0014510717;
                        } else {
                            var9 = -0.0028820958;
                        }
                    } else {
                        if (input[3] < 1.6) {
                            var9 = -0.0016560855;
                        } else {
                            var9 = 0.0007554209;
                        }
                    }
                } else {
                    if (input[0] < 78.0) {
                        if (input[1] < 5.0) {
                            var9 = -0.0017958934;
                        } else {
                            var9 = -0.00057261705;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var9 = -0.0011193728;
                        } else {
                            var9 = -0.0027570485;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[0] < 52.0) {
                        if (input[3] < 1.67) {
                            var9 = -0.0017935665;
                        } else {
                            var9 = 0.00014878564;
                        }
                    } else {
                        if (input[3] < 1.82) {
                            var9 = -0.0036201128;
                        } else {
                            var9 = -0.0014009237;
                        }
                    }
                } else {
                    if (input[4] < 2.0) {
                        if (input[0] < 13.0) {
                            var9 = 0.004265228;
                        } else {
                            var9 = -0.0033978936;
                        }
                    } else {
                        if (input[3] < 1.51) {
                            var9 = 0.0014194045;
                        } else {
                            var9 = 0.006686757;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var9 = -0.0021820487;
                        } else {
                            var9 = -0.00097275194;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var9 = -0.00019090196;
                        } else {
                            var9 = -0.0010738093;
                        }
                    }
                } else {
                    if (input[0] < 92.0) {
                        if (input[3] < 2.68) {
                            var9 = 0.0023680031;
                        } else {
                            var9 = 0.00043761148;
                        }
                    } else {
                        if (input[3] < 3.62) {
                            var9 = -0.0030374618;
                        } else {
                            var9 = 0.00094854675;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[3] < 4.41) {
                        if (input[0] < 25.0) {
                            var9 = 0.0021981546;
                        } else {
                            var9 = -0.00066839176;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var9 = 0.0026011753;
                        } else {
                            var9 = 0.0060648127;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 43.0) {
                            var9 = 0.0019389734;
                        } else {
                            var9 = -0.0031779283;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var9 = 0.003057405;
                        } else {
                            var9 = 0.008845238;
                        }
                    }
                }
            }
        }
        double var10;
        if (input[3] < 3.59) {
            if (input[0] < 25.0) {
                if (input[1] < 2.0) {
                    if (input[3] < 2.9) {
                        if (input[3] < 1.55) {
                            var10 = -0.0013078683;
                        } else {
                            var10 = -0.002889289;
                        }
                    } else {
                        if (input[3] < 3.13) {
                            var10 = 0.00073468505;
                        } else {
                            var10 = -0.0023862037;
                        }
                    }
                } else {
                    if (input[3] < 1.51) {
                        if (input[2] < 4.0) {
                            var10 = -0.002376334;
                        } else {
                            var10 = -0.00035587916;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var10 = -0.0031759315;
                        } else {
                            var10 = 0.0022783855;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[0] < 78.0) {
                        if (input[1] < 5.0) {
                            var10 = -0.0012037798;
                        } else {
                            var10 = -0.0000056528693;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var10 = -0.0008561641;
                        } else {
                            var10 = -0.002288184;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.82) {
                            var10 = -0.0027247153;
                        } else {
                            var10 = -0.0008707914;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var10 = -0.0016220011;
                        } else {
                            var10 = 0.006853949;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var10 = -0.00085710635;
                    } else {
                        if (input[2] < 9.0) {
                            var10 = -0.00031262144;
                        } else {
                            var10 = -0.00020053388;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.85) {
                            var10 = 0.0033111863;
                        } else {
                            var10 = 0.00095468323;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var10 = -0.0006130994;
                        } else {
                            var10 = 0.0010679147;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[3] < 4.43) {
                        if (input[2] < 4.0) {
                            var10 = -0.0016306051;
                        } else {
                            var10 = 0.0013447178;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var10 = 0.0023643773;
                        } else {
                            var10 = 0.005400622;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[3] < 3.93) {
                            var10 = 0.0064773597;
                        } else {
                            var10 = 0.00061338575;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var10 = 0.0018442636;
                        } else {
                            var10 = 0.015508251;
                        }
                    }
                }
            }
        }
        double var11;
        if (input[3] < 2.46) {
            if (input[2] < 4.0) {
                if (input[1] < 2.0) {
                    if (input[0] < 11.0) {
                        if (input[3] < 1.77) {
                            var11 = -0.0012461633;
                        } else {
                            var11 = -0.0047532185;
                        }
                    } else {
                        if (input[0] < 61.0) {
                            var11 = -0.0012400736;
                        } else {
                            var11 = -0.0003558976;
                        }
                    }
                } else {
                    if (input[0] < 12.0) {
                        if (input[3] < 1.72) {
                            var11 = -0.0019960464;
                        } else {
                            var11 = 0.00090460555;
                        }
                    } else {
                        if (input[3] < 1.31) {
                            var11 = -0.0011930221;
                        } else {
                            var11 = -0.0026711728;
                        }
                    }
                }
            } else {
                if (input[3] < 1.35) {
                    if (input[1] < 2.0) {
                        if (input[0] < 40.0) {
                            var11 = -0.0004891457;
                        } else {
                            var11 = 0.0001248662;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var11 = -0.004651557;
                        } else {
                            var11 = -0.0019358157;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[1] < 4.0) {
                            var11 = -0.0013682245;
                        } else {
                            var11 = -0.006418956;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var11 = -0.0015919864;
                        } else {
                            var11 = 0.0015983032;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var11 = -0.0019933486;
                        } else {
                            var11 = -0.0008681511;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var11 = 0.00013212158;
                        } else {
                            var11 = -0.0004986076;
                        }
                    }
                } else {
                    if (input[0] < 92.0) {
                        if (input[3] < 2.68) {
                            var11 = 0.0022472355;
                        } else {
                            var11 = 0.0003924241;
                        }
                    } else {
                        if (input[3] < 3.62) {
                            var11 = -0.0027931216;
                        } else {
                            var11 = 0.000881375;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[3] < 4.43) {
                        if (input[0] < 37.0) {
                            var11 = 0.0016249197;
                        } else {
                            var11 = -0.001056021;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var11 = 0.0028172303;
                        } else {
                            var11 = 0.0065728696;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.14) {
                            var11 = -0.00047630738;
                        } else {
                            var11 = 0.004289719;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var11 = 0.0035261405;
                        } else {
                            var11 = 0.01281887;
                        }
                    }
                }
            }
        }
        double var12;
        if (input[3] < 3.2) {
            if (input[2] < 4.0) {
                if (input[0] < 19.0) {
                    if (input[3] < 2.55) {
                        if (input[1] < 2.0) {
                            var12 = -0.0026249303;
                        } else {
                            var12 = -0.00093743764;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var12 = -0.0007518655;
                        } else {
                            var12 = 0.0016267616;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.85) {
                            var12 = -0.0010299128;
                        } else {
                            var12 = 0.0015505528;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var12 = -0.0010420909;
                        } else {
                            var12 = -0.0022863846;
                        }
                    }
                }
            } else {
                if (input[3] < 1.45) {
                    if (input[0] < 27.0) {
                        if (input[1] < 3.0) {
                            var12 = -0.0019638983;
                        } else {
                            var12 = 0.00043590236;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var12 = -0.00041422347;
                        } else {
                            var12 = -0.0029117158;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[1] < 4.0) {
                            var12 = -0.0016385829;
                        } else {
                            var12 = -0.0043451716;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var12 = -0.0021282076;
                        } else {
                            var12 = 0.0011729468;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 3.36) {
                        var12 = -0.0011404267;
                    } else {
                        var12 = -0.000773043;
                    }
                } else {
                    if (input[3] < 3.32) {
                        if (input[2] < 9.0) {
                            var12 = -0.00035840363;
                        } else {
                            var12 = -0.0017638654;
                        }
                    } else {
                        if (input[3] < 3.43) {
                            var12 = 0.000018344428;
                        } else {
                            var12 = -0.0002704198;
                        }
                    }
                }
            } else {
                if (input[0] < 60.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.88) {
                            var12 = -0.00061834056;
                        } else {
                            var12 = 0.006863383;
                        }
                    } else {
                        if (input[4] < 5.0) {
                            var12 = 0.0012281548;
                        } else {
                            var12 = 0.0043223784;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 2.0) {
                            var12 = 0.0020058942;
                        } else {
                            var12 = -0.0029743141;
                        }
                    } else {
                        if (input[3] < 4.77) {
                            var12 = 0.0006345491;
                        } else {
                            var12 = 0.0039404053;
                        }
                    }
                }
            }
        }
        double var13;
        if (input[3] < 3.59) {
            if (input[0] < 14.0) {
                if (input[1] < 2.0) {
                    if (input[3] < 2.78) {
                        if (input[3] < 1.55) {
                            var13 = -0.0008004467;
                        } else {
                            var13 = -0.0032191314;
                        }
                    } else {
                        if (input[3] < 3.13) {
                            var13 = 0.000434979;
                        } else {
                            var13 = -0.0018782824;
                        }
                    }
                } else {
                    if (input[3] < 1.49) {
                        if (input[2] < 9.0) {
                            var13 = -0.0015454289;
                        } else {
                            var13 = 0.0013379534;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var13 = -0.0022573888;
                        } else {
                            var13 = 0.002732849;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[0] < 78.0) {
                        if (input[3] < 1.77) {
                            var13 = -0.0014392514;
                        } else {
                            var13 = -0.00044455222;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var13 = -0.0011511954;
                        } else {
                            var13 = -0.0022716222;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[0] < 75.0) {
                            var13 = -0.0005419958;
                        } else {
                            var13 = -0.002159637;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var13 = -0.0015697591;
                        } else {
                            var13 = 0.0061816243;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var13 = -0.00073315523;
                    } else {
                        if (input[2] < 9.0) {
                            var13 = -0.00027119226;
                        } else {
                            var13 = -0.00015552556;
                        }
                    }
                } else {
                    if (input[0] < 47.0) {
                        if (input[3] < 3.79) {
                            var13 = 0.0035585822;
                        } else {
                            var13 = 0.0011727505;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var13 = 0.0013190253;
                        } else {
                            var13 = -0.000038048496;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.86) {
                        if (input[0] < 73.0) {
                            var13 = 0.0004883797;
                        } else {
                            var13 = -0.0028759218;
                        }
                    } else {
                        var13 = 0.007067633;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var13 = -0.0026675714;
                        } else {
                            var13 = 0.0048840046;
                        }
                    } else {
                        if (input[3] < 4.27) {
                            var13 = 0.005549978;
                        } else {
                            var13 = 0.014433729;
                        }
                    }
                }
            }
        }
        double var14;
        if (input[3] < 2.46) {
            if (input[0] < 40.0) {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 1.31) {
                            var14 = -0.0003430693;
                        } else {
                            var14 = -0.0021169807;
                        }
                    } else {
                        if (input[3] < 1.67) {
                            var14 = -0.002240774;
                        } else {
                            var14 = 0.000374373;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.72) {
                            var14 = -0.0021206408;
                        } else {
                            var14 = -0.00021301133;
                        }
                    } else {
                        if (input[3] < 1.18) {
                            var14 = -0.0030555723;
                        } else {
                            var14 = 0.002058647;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[2] < 9.0) {
                        if (input[1] < 2.0) {
                            var14 = -0.0011559686;
                        } else {
                            var14 = -0.001962182;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var14 = -0.0020418898;
                        } else {
                            var14 = 0.0031440293;
                        }
                    }
                } else {
                    if (input[3] < 1.47) {
                        if (input[4] < 1.0) {
                            var14 = 0.0006151825;
                        } else {
                            var14 = -0.0024517025;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var14 = -0.0041155354;
                        } else {
                            var14 = 0.0010248151;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var14 = -0.0017534172;
                        } else {
                            var14 = -0.0007365734;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var14 = -0.00011606596;
                        } else {
                            var14 = -0.0009434793;
                        }
                    }
                } else {
                    if (input[3] < 2.68) {
                        if (input[2] < 7.0) {
                            var14 = 0.0002765029;
                        } else {
                            var14 = 0.00539373;
                        }
                    } else {
                        if (input[3] < 3.07) {
                            var14 = -0.0014315534;
                        } else {
                            var14 = 0.0005949851;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[3] < 4.65) {
                        if (input[0] < 25.0) {
                            var14 = 0.0019941218;
                        } else {
                            var14 = -0.0004757295;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var14 = 0.002817549;
                        } else {
                            var14 = 0.006654507;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.4) {
                            var14 = -0.0012590257;
                        } else {
                            var14 = 0.003838459;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var14 = 0.0026009993;
                        } else {
                            var14 = 0.007568229;
                        }
                    }
                }
            }
        }
        double var15;
        if (input[3] < 3.69) {
            if (input[0] < 73.0) {
                if (input[1] < 3.0) {
                    if (input[3] < 2.06) {
                        if (input[0] < 4.0) {
                            var15 = -0.000047820064;
                        } else {
                            var15 = -0.0016582232;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var15 = -0.0009825734;
                        } else {
                            var15 = -0.00016292374;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 1.43) {
                            var15 = 0.00045590792;
                        } else {
                            var15 = -0.0028128559;
                        }
                    } else {
                        if (input[3] < 1.53) {
                            var15 = -0.0012341145;
                        } else {
                            var15 = 0.0013181246;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[4] < 3.0) {
                        if (input[3] < 2.16) {
                            var15 = -0.0016762529;
                        } else {
                            var15 = -0.00056844857;
                        }
                    } else {
                        if (input[0] < 91.0) {
                            var15 = -0.0014852389;
                        } else {
                            var15 = -0.002749005;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[4] < 5.0) {
                            var15 = -0.0010854407;
                        } else {
                            var15 = -0.0028403408;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var15 = -0.0028517132;
                        } else {
                            var15 = 0.005118035;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var15 = -0.0006598049;
                    } else {
                        if (input[2] < 9.0) {
                            var15 = -0.00025186452;
                        } else {
                            var15 = -0.00010450652;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 4.96) {
                            var15 = 0.0013953982;
                        } else {
                            var15 = 0.004699489;
                        }
                    } else {
                        if (input[3] < 3.84) {
                            var15 = 0.0031053093;
                        } else {
                            var15 = 0.00007925406;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var15 = 0.0015562907;
                        } else {
                            var15 = -0.0049530515;
                        }
                    } else {
                        if (input[3] < 4.48) {
                            var15 = 0.00012271582;
                        } else {
                            var15 = 0.005024843;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var15 = -0.0025767905;
                        } else {
                            var15 = 0.0044787787;
                        }
                    } else {
                        if (input[3] < 4.27) {
                            var15 = 0.005124504;
                        } else {
                            var15 = 0.013482998;
                        }
                    }
                }
            }
        }
        double var16;
        if (input[3] < 2.46) {
            if (input[2] < 4.0) {
                if (input[3] < 1.31) {
                    if (input[0] < 60.0) {
                        if (input[1] < 5.0) {
                            var16 = -0.0010048561;
                        } else {
                            var16 = -0.0024744666;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var16 = 0.0002809502;
                        } else {
                            var16 = -0.0012627806;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[0] < 11.0) {
                            var16 = -0.0032291175;
                        } else {
                            var16 = -0.0006390581;
                        }
                    } else {
                        if (input[0] < 12.0) {
                            var16 = 0.000081497245;
                        } else {
                            var16 = -0.0022983502;
                        }
                    }
                }
            } else {
                if (input[3] < 1.21) {
                    if (input[1] < 2.0) {
                        if (input[0] < 57.0) {
                            var16 = 0.000117972726;
                        } else {
                            var16 = 0.00045833836;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var16 = -0.0041085775;
                        } else {
                            var16 = -0.0021573082;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var16 = -0.0019873844;
                        } else {
                            var16 = -0.0008358173;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var16 = -0.0038899023;
                        } else {
                            var16 = 0.0012443735;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var16 = -0.0015896587;
                        } else {
                            var16 = -0.00064927555;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var16 = 0.00016823021;
                        } else {
                            var16 = -0.0004311352;
                        }
                    }
                } else {
                    if (input[3] < 2.68) {
                        if (input[2] < 7.0) {
                            var16 = 0.0002674876;
                        } else {
                            var16 = 0.0050831977;
                        }
                    } else {
                        if (input[3] < 3.07) {
                            var16 = -0.0013696714;
                        } else {
                            var16 = 0.00053015345;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[3] < 4.43) {
                        if (input[0] < 37.0) {
                            var16 = 0.0013202988;
                        } else {
                            var16 = -0.001030758;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var16 = 0.002294502;
                        } else {
                            var16 = 0.005487435;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.14) {
                            var16 = -0.00045406583;
                        } else {
                            var16 = 0.0038170933;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var16 = 0.0030653893;
                        } else {
                            var16 = 0.011122089;
                        }
                    }
                }
            }
        }
        double var17;
        if (input[3] < 2.42) {
            if (input[0] < 40.0) {
                if (input[1] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 1.55) {
                            var17 = -0.00075175444;
                        } else {
                            var17 = -0.0022385267;
                        }
                    } else {
                        if (input[3] < 1.6) {
                            var17 = -0.0015535336;
                        } else {
                            var17 = 0.0005409615;
                        }
                    }
                } else {
                    if (input[2] < 6.0) {
                        if (input[4] < 1.0) {
                            var17 = -0.003947323;
                        } else {
                            var17 = -0.000327217;
                        }
                    } else {
                        if (input[3] < 1.49) {
                            var17 = 0.00011335714;
                        } else {
                            var17 = 0.0040176976;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 76.0) {
                        if (input[3] < 2.01) {
                            var17 = -0.0014394997;
                        } else {
                            var17 = -0.0006293884;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var17 = -0.0008368916;
                        } else {
                            var17 = -0.002147608;
                        }
                    }
                } else {
                    if (input[3] < 1.47) {
                        if (input[4] < 1.0) {
                            var17 = 0.000736285;
                        } else {
                            var17 = -0.0022374336;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var17 = -0.0036790904;
                        } else {
                            var17 = 0.0009975117;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var17 = 0.00075261865;
                        } else {
                            var17 = -0.0021636784;
                        }
                    } else {
                        if (input[3] < 2.82) {
                            var17 = 0.00047629475;
                        } else {
                            var17 = -0.0006307005;
                        }
                    }
                } else {
                    if (input[3] < 2.68) {
                        if (input[1] < 2.0) {
                            var17 = -0.0018527899;
                        } else {
                            var17 = 0.0017721554;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var17 = 0.00041865796;
                        } else {
                            var17 = -0.0005546293;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[3] < 4.43) {
                        if (input[0] < 40.0) {
                            var17 = 0.0011660134;
                        } else {
                            var17 = -0.0010555524;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var17 = 0.0016422233;
                        } else {
                            var17 = 0.004145528;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.0) {
                            var17 = -0.0005125769;
                        } else {
                            var17 = 0.0033987225;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var17 = 0.0029157654;
                        } else {
                            var17 = 0.010580242;
                        }
                    }
                }
            }
        }
        double var18;
        if (input[3] < 3.59) {
            if (input[0] < 14.0) {
                if (input[1] < 2.0) {
                    if (input[3] < 2.78) {
                        if (input[3] < 1.55) {
                            var18 = -0.0005630261;
                        } else {
                            var18 = -0.0028160748;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var18 = 0.00029504072;
                        } else {
                            var18 = -0.0022271702;
                        }
                    }
                } else {
                    if (input[3] < 1.49) {
                        if (input[2] < 9.0) {
                            var18 = -0.0012597586;
                        } else {
                            var18 = 0.0014467895;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var18 = -0.00075427577;
                        } else {
                            var18 = 0.0026183834;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[0] < 78.0) {
                        if (input[1] < 5.0) {
                            var18 = -0.00078567764;
                        } else {
                            var18 = 0.00014067741;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var18 = -0.00042855492;
                        } else {
                            var18 = -0.0017600813;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[0] < 82.0) {
                            var18 = -0.0005020592;
                        } else {
                            var18 = -0.0021023438;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var18 = -0.0014368326;
                        } else {
                            var18 = 0.005353048;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var18 = -0.0005630815;
                    } else {
                        if (input[2] < 4.0) {
                            var18 = -0.0002852371;
                        } else {
                            var18 = -0.0001768299;
                        }
                    }
                } else {
                    if (input[0] < 47.0) {
                        if (input[3] < 3.79) {
                            var18 = 0.0031924786;
                        } else {
                            var18 = 0.00091669866;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var18 = 0.0008703216;
                        } else {
                            var18 = -0.00044662837;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.86) {
                        if (input[1] < 2.0) {
                            var18 = -0.0022880451;
                        } else {
                            var18 = 0.00064214424;
                        }
                    } else {
                        if (input[2] < 3.0) {
                            var18 = 0.0033875348;
                        } else {
                            var18 = 0.0072619156;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.73) {
                            var18 = -0.0030257439;
                        } else {
                            var18 = 0.002233667;
                        }
                    } else {
                        if (input[3] < 4.27) {
                            var18 = 0.00455453;
                        } else {
                            var18 = 0.012269143;
                        }
                    }
                }
            }
        }
        double var19;
        if (input[3] < 2.42) {
            if (input[0] < 25.0) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.67) {
                        if (input[3] < 1.4) {
                            var19 = -0.0012192706;
                        } else {
                            var19 = -0.002421785;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var19 = -0.0032012425;
                        } else {
                            var19 = 0.000032074557;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 1.6) {
                            var19 = -0.0015298531;
                        } else {
                            var19 = -0.0000749082;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var19 = -0.0035270483;
                        } else {
                            var19 = 0.0024483292;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 76.0) {
                        if (input[2] < 9.0) {
                            var19 = -0.0011697697;
                        } else {
                            var19 = -0.00020268431;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var19 = -0.0007610403;
                        } else {
                            var19 = -0.0019669272;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 2.0) {
                            var19 = -0.0017076243;
                        } else {
                            var19 = -0.00021518285;
                        }
                    } else {
                        if (input[3] < 1.55) {
                            var19 = -0.0013180211;
                        } else {
                            var19 = 0.0034626354;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var19 = 0.0007515468;
                        } else {
                            var19 = -0.0020106274;
                        }
                    } else {
                        if (input[3] < 2.82) {
                            var19 = 0.00048483812;
                        } else {
                            var19 = -0.00056633505;
                        }
                    }
                } else {
                    if (input[3] < 2.68) {
                        if (input[1] < 4.0) {
                            var19 = -0.00013765371;
                        } else {
                            var19 = 0.0028629375;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var19 = 0.00039154428;
                        } else {
                            var19 = -0.000525688;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[3] < 4.65) {
                        if (input[0] < 59.0) {
                            var19 = 0.0008191215;
                        } else {
                            var19 = -0.0011159496;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var19 = 0.0022576265;
                        } else {
                            var19 = 0.0054929717;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 43.0) {
                            var19 = 0.0014131776;
                        } else {
                            var19 = -0.0027401817;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var19 = 0.0022816441;
                        } else {
                            var19 = 0.0063477233;
                        }
                    }
                }
            }
        }
        double var20;
        if (input[3] < 2.93) {
            if (input[1] < 5.0) {
                if (input[2] < 9.0) {
                    if (input[0] < 31.0) {
                        if (input[1] < 2.0) {
                            var20 = -0.0017769454;
                        } else {
                            var20 = 0.000015861551;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var20 = -0.0008714786;
                        } else {
                            var20 = -0.0014791808;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.68) {
                            var20 = -0.0018325739;
                        } else {
                            var20 = -0.00054670795;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var20 = -0.0011838117;
                        } else {
                            var20 = 0.0062216595;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 10.0) {
                        if (input[0] < 6.0) {
                            var20 = -0.0011900817;
                        } else {
                            var20 = 0.002103431;
                        }
                    } else {
                        if (input[0] < 95.0) {
                            var20 = -0.0011502162;
                        } else {
                            var20 = -0.0032157083;
                        }
                    }
                } else {
                    if (input[4] < 2.0) {
                        if (input[3] < 1.7) {
                            var20 = 0.0007406423;
                        } else {
                            var20 = -0.0027992155;
                        }
                    } else {
                        if (input[3] < 1.55) {
                            var20 = -0.0010132504;
                        } else {
                            var20 = 0.0037824733;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[2] < 9.0) {
                        if (input[4] < 1.0) {
                            var20 = -0.0005012613;
                        } else {
                            var20 = -0.00014071925;
                        }
                    } else {
                        if (input[3] < 3.36) {
                            var20 = -0.0016986653;
                        } else {
                            var20 = -0.00031415903;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[2] < 3.0) {
                            var20 = 0.00039210502;
                        } else {
                            var20 = 0.0011108654;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var20 = 0.0017845113;
                        } else {
                            var20 = -0.0017574898;
                        }
                    }
                }
            } else {
                if (input[0] < 60.0) {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var20 = 0.0011193679;
                        } else {
                            var20 = -0.0029965197;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var20 = 0.0020807562;
                        } else {
                            var20 = 0.0053195893;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 2.0) {
                            var20 = 0.002648087;
                        } else {
                            var20 = -0.004305725;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var20 = -0.0019494718;
                        } else {
                            var20 = 0.0015540043;
                        }
                    }
                }
            }
        }
        double var21;
        if (input[3] < 3.69) {
            if (input[0] < 25.0) {
                if (input[1] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[0] < 21.0) {
                            var21 = 0.00010005768;
                        } else {
                            var21 = -0.0017026216;
                        }
                    } else {
                        if (input[3] < 1.55) {
                            var21 = -0.00066490594;
                        } else {
                            var21 = -0.0019615646;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 1.88) {
                            var21 = 0.00018502703;
                        } else {
                            var21 = -0.0028302372;
                        }
                    } else {
                        if (input[3] < 1.67) {
                            var21 = -0.00059878937;
                        } else {
                            var21 = 0.001907661;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[0] < 90.0) {
                        if (input[1] < 5.0) {
                            var21 = -0.0008914064;
                        } else {
                            var21 = 0.000106458974;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var21 = -0.00080900703;
                        } else {
                            var21 = -0.002290211;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.82) {
                            var21 = -0.0019370404;
                        } else {
                            var21 = -0.0005413454;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var21 = -0.00038180317;
                        } else {
                            var21 = 0.005749851;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var21 = -0.00048450736;
                    } else {
                        if (input[1] < 4.0) {
                            var21 = -0.00024253181;
                        } else {
                            var21 = -0.0001302834;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 4.96) {
                            var21 = 0.0011640805;
                        } else {
                            var21 = 0.0041420255;
                        }
                    } else {
                        if (input[3] < 3.84) {
                            var21 = 0.0027825094;
                        } else {
                            var21 = -0.000104466184;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var21 = 0.0013297272;
                        } else {
                            var21 = -0.0046140207;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var21 = -0.001676217;
                        } else {
                            var21 = 0.002797771;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var21 = -0.002480368;
                        } else {
                            var21 = 0.0037632564;
                        }
                    } else {
                        if (input[3] < 4.35) {
                            var21 = 0.004566892;
                        } else {
                            var21 = 0.012132379;
                        }
                    }
                }
            }
        }
        double var22;
        if (input[3] < 2.42) {
            if (input[0] < 40.0) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.72) {
                        if (input[3] < 1.4) {
                            var22 = -0.0011337365;
                        } else {
                            var22 = -0.0022401523;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var22 = -0.0021939923;
                        } else {
                            var22 = -0.00019475367;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 1.6) {
                            var22 = -0.0013874471;
                        } else {
                            var22 = -0.00027158877;
                        }
                    } else {
                        if (input[3] < 1.15) {
                            var22 = -0.0029751298;
                        } else {
                            var22 = 0.0016955478;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 76.0) {
                        if (input[3] < 2.01) {
                            var22 = -0.0011826024;
                        } else {
                            var22 = -0.0004302142;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var22 = -0.0006040342;
                        } else {
                            var22 = -0.0017649574;
                        }
                    }
                } else {
                    if (input[3] < 1.26) {
                        if (input[4] < 1.0) {
                            var22 = 0.0006446207;
                        } else {
                            var22 = -0.0029765046;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var22 = -0.0017711179;
                        } else {
                            var22 = 0.0006136351;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 19.0) {
                if (input[4] < 4.0) {
                    if (input[4] < 2.0) {
                        if (input[3] < 2.55) {
                            var22 = -0.0021557321;
                        } else {
                            var22 = -0.0004584271;
                        }
                    } else {
                        if (input[3] < 2.66) {
                            var22 = 0.0031970402;
                        } else {
                            var22 = 0.00027089493;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.81) {
                            var22 = -0.0022668343;
                        } else {
                            var22 = 0.005364915;
                        }
                    } else {
                        if (input[3] < 3.5) {
                            var22 = 0.0024343536;
                        } else {
                            var22 = 0.004839128;
                        }
                    }
                }
            } else {
                if (input[3] < 4.49) {
                    if (input[2] < 9.0) {
                        if (input[0] < 75.0) {
                            var22 = -0.00010179396;
                        } else {
                            var22 = -0.0010001727;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var22 = -0.00064294063;
                        } else {
                            var22 = 0.0021790115;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 2.0) {
                            var22 = -0.00033117723;
                        } else {
                            var22 = 0.00094444724;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var22 = -0.0024069713;
                        } else {
                            var22 = 0.0054920106;
                        }
                    }
                }
            }
        }
        double var23;
        if (input[3] < 2.42) {
            if (input[2] < 4.0) {
                if (input[3] < 1.31) {
                    if (input[0] < 60.0) {
                        if (input[1] < 5.0) {
                            var23 = -0.0006691699;
                        } else {
                            var23 = -0.0021117085;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var23 = 0.0006566247;
                        } else {
                            var23 = -0.00090138486;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[0] < 11.0) {
                            var23 = -0.0027165206;
                        } else {
                            var23 = -0.00033803677;
                        }
                    } else {
                        if (input[0] < 30.0) {
                            var23 = -0.00062646397;
                        } else {
                            var23 = -0.0020793378;
                        }
                    }
                }
            } else {
                if (input[3] < 1.21) {
                    if (input[1] < 2.0) {
                        if (input[0] < 26.0) {
                            var23 = 0.00023082733;
                        } else {
                            var23 = 0.0006419419;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var23 = -0.0035698009;
                        } else {
                            var23 = -0.0018490503;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var23 = -0.0016087631;
                        } else {
                            var23 = -0.0005991684;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var23 = -0.0035400793;
                        } else {
                            var23 = 0.0011512891;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 19.0) {
                if (input[4] < 4.0) {
                    if (input[4] < 2.0) {
                        if (input[3] < 2.55) {
                            var23 = -0.0020695007;
                        } else {
                            var23 = -0.00043577934;
                        }
                    } else {
                        if (input[3] < 2.66) {
                            var23 = 0.0030486074;
                        } else {
                            var23 = 0.00025751023;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.81) {
                            var23 = -0.0021563235;
                        } else {
                            var23 = 0.005186085;
                        }
                    } else {
                        if (input[3] < 3.5) {
                            var23 = 0.0023144844;
                        } else {
                            var23 = 0.004600055;
                        }
                    }
                }
            } else {
                if (input[3] < 4.49) {
                    if (input[2] < 9.0) {
                        if (input[2] < 4.0) {
                            var23 = -0.00080994325;
                        } else {
                            var23 = 0.0000022926895;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var23 = -0.00061117107;
                        } else {
                            var23 = 0.0020708155;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 2.0) {
                            var23 = -0.00031486995;
                        } else {
                            var23 = 0.0008975124;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var23 = -0.0022958757;
                        } else {
                            var23 = 0.0052232454;
                        }
                    }
                }
            }
        }
        double var24;
        if (input[3] < 3.69) {
            if (input[0] < 73.0) {
                if (input[1] < 3.0) {
                    if (input[3] < 2.06) {
                        if (input[3] < 1.94) {
                            var24 = -0.00093732914;
                        } else {
                            var24 = -0.0021018495;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var24 = -0.0006971808;
                        } else {
                            var24 = -0.0001435502;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 1.43) {
                            var24 = 0.0008063234;
                        } else {
                            var24 = -0.0022635697;
                        }
                    } else {
                        if (input[3] < 1.3) {
                            var24 = -0.0015369848;
                        } else {
                            var24 = 0.0009430816;
                        }
                    }
                }
            } else {
                if (input[3] < 3.63) {
                    if (input[2] < 9.0) {
                        if (input[1] < 2.0) {
                            var24 = -0.00030265236;
                        } else {
                            var24 = -0.0013254514;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var24 = -0.0014582245;
                        } else {
                            var24 = 0.0020625347;
                        }
                    }
                } else {
                    if (input[2] < 2.0) {
                        var24 = -0.006989789;
                    } else {
                        if (input[4] < 2.0) {
                            var24 = -0.00022898764;
                        } else {
                            var24 = -0.00245898;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var24 = -0.00041951332;
                    } else {
                        if (input[2] < 9.0) {
                            var24 = -0.00016562655;
                        } else {
                            var24 = 0.000009110996;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 4.96) {
                            var24 = 0.0010791988;
                        } else {
                            var24 = 0.003790859;
                        }
                    } else {
                        if (input[3] < 3.84) {
                            var24 = 0.0025717884;
                        } else {
                            var24 = -0.00018160252;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.86) {
                        if (input[1] < 2.0) {
                            var24 = -0.0018923002;
                        } else {
                            var24 = 0.00053697365;
                        }
                    } else {
                        if (input[2] < 3.0) {
                            var24 = 0.002721243;
                        } else {
                            var24 = 0.0062355567;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var24 = -0.0025351683;
                        } else {
                            var24 = 0.0031968735;
                        }
                    } else {
                        if (input[3] < 4.27) {
                            var24 = 0.0037217706;
                        } else {
                            var24 = 0.010440311;
                        }
                    }
                }
            }
        }
        double var25;
        if (input[3] < 2.42) {
            if (input[0] < 25.0) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.67) {
                        if (input[1] < 2.0) {
                            var25 = -0.00026570883;
                        } else {
                            var25 = -0.0016211888;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var25 = -0.0026508893;
                        } else {
                            var25 = -0.000020042451;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[3] < 1.6) {
                            var25 = -0.001210011;
                        } else {
                            var25 = 0.00007733703;
                        }
                    } else {
                        if (input[3] < 1.11) {
                            var25 = -0.0029864968;
                        } else {
                            var25 = 0.0021212373;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 76.0) {
                        if (input[2] < 9.0) {
                            var25 = -0.00091448164;
                        } else {
                            var25 = -0.000047389956;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var25 = -0.00051319087;
                        } else {
                            var25 = -0.0015907763;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[2] < 6.0) {
                            var25 = -0.0012053213;
                        } else {
                            var25 = -0.000012478788;
                        }
                    } else {
                        if (input[3] < 1.27) {
                            var25 = -0.0025664975;
                        } else {
                            var25 = 0.0024418344;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 24.0) {
                if (input[4] < 4.0) {
                    if (input[2] < 10.0) {
                        if (input[4] < 2.0) {
                            var25 = -0.00050735247;
                        } else {
                            var25 = 0.00083566335;
                        }
                    } else {
                        if (input[3] < 3.8) {
                            var25 = -0.0027477832;
                        } else {
                            var25 = -0.000024434654;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.81) {
                            var25 = -0.0021951245;
                        } else {
                            var25 = 0.0051284134;
                        }
                    } else {
                        if (input[3] < 3.5) {
                            var25 = 0.002015339;
                        } else {
                            var25 = 0.0040281652;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[3] < 4.44) {
                        if (input[2] < 4.0) {
                            var25 = -0.0008552312;
                        } else {
                            var25 = -0.00002899239;
                        }
                    } else {
                        if (input[4] < 7.0) {
                            var25 = 0.0005608412;
                        } else {
                            var25 = 0.002900481;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 2.0) {
                            var25 = -0.0008275546;
                        } else {
                            var25 = 0.0008764497;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var25 = -0.0002815506;
                        } else {
                            var25 = 0.008019451;
                        }
                    }
                }
            }
        }
        double var26;
        if (input[3] < 2.42) {
            if (input[0] < 40.0) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.72) {
                        if (input[3] < 1.4) {
                            var26 = -0.0009284057;
                        } else {
                            var26 = -0.0020471504;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var26 = -0.0019116107;
                        } else {
                            var26 = -0.00014336572;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.28) {
                            var26 = -0.001786955;
                        } else {
                            var26 = -0.000056422792;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var26 = -0.0024368518;
                        } else {
                            var26 = 0.0022856859;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 76.0) {
                        if (input[3] < 2.01) {
                            var26 = -0.001016818;
                        } else {
                            var26 = -0.0003214052;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var26 = -0.00048798122;
                        } else {
                            var26 = -0.0015117367;
                        }
                    }
                } else {
                    if (input[3] < 1.47) {
                        if (input[4] < 1.0) {
                            var26 = 0.0009200536;
                        } else {
                            var26 = -0.0018816168;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var26 = -0.0029676794;
                        } else {
                            var26 = 0.0007934265;
                        }
                    }
                }
            }
        } else {
            if (input[0] < 43.0) {
                if (input[4] < 4.0) {
                    if (input[4] < 2.0) {
                        if (input[1] < 2.0) {
                            var26 = 0.00034031525;
                        } else {
                            var26 = -0.0006439273;
                        }
                    } else {
                        if (input[2] < 8.0) {
                            var26 = 0.00079523865;
                        } else {
                            var26 = -0.00054986106;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 4.29) {
                            var26 = -0.002359663;
                        } else {
                            var26 = 0.0015684894;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var26 = 0.0018257505;
                        } else {
                            var26 = 0.0040751337;
                        }
                    }
                }
            } else {
                if (input[3] < 4.86) {
                    if (input[2] < 4.0) {
                        if (input[4] < 4.0) {
                            var26 = -0.000022298036;
                        } else {
                            var26 = -0.0018252743;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var26 = -0.00081167975;
                        } else {
                            var26 = 0.00042569498;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 2.0) {
                            var26 = 0.0033440397;
                        } else {
                            var26 = 0.00018849969;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var26 = 0.0014564533;
                        } else {
                            var26 = 0.009245018;
                        }
                    }
                }
            }
        }
        double var27;
        if (input[3] < 3.69) {
            if (input[0] < 15.0) {
                if (input[1] < 2.0) {
                    if (input[3] < 2.14) {
                        if (input[3] < 1.55) {
                            var27 = -0.0001564911;
                        } else {
                            var27 = -0.0036653138;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var27 = 0.00027808032;
                        } else {
                            var27 = -0.0014763775;
                        }
                    }
                } else {
                    if (input[3] < 3.51) {
                        if (input[3] < 1.49) {
                            var27 = -0.0005202166;
                        } else {
                            var27 = 0.0013315742;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var27 = -0.00022046418;
                        } else {
                            var27 = 0.0063071973;
                        }
                    }
                }
            } else {
                if (input[0] < 90.0) {
                    if (input[2] < 9.0) {
                        if (input[1] < 5.0) {
                            var27 = -0.00063215516;
                        } else {
                            var27 = 0.00022418924;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var27 = -0.0005004236;
                        } else {
                            var27 = 0.0021568607;
                        }
                    }
                } else {
                    if (input[3] < 3.24) {
                        if (input[3] < 2.18) {
                            var27 = -0.0015251226;
                        } else {
                            var27 = -0.00046410828;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var27 = -0.0002727989;
                        } else {
                            var27 = -0.003912738;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var27 = -0.00038056247;
                    } else {
                        if (input[2] < 9.0) {
                            var27 = -0.00014434048;
                        } else {
                            var27 = 0.000050249273;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[0] < 95.0) {
                            var27 = 0.0009968607;
                        } else {
                            var27 = 0.0035985436;
                        }
                    } else {
                        if (input[3] < 3.84) {
                            var27 = 0.002370823;
                        } else {
                            var27 = -0.00023778112;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.86) {
                        if (input[0] < 81.0) {
                            var27 = 0.00009970579;
                        } else {
                            var27 = -0.0028225856;
                        }
                    } else {
                        if (input[2] < 3.0) {
                            var27 = 0.0024344206;
                        } else {
                            var27 = 0.00566154;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.82) {
                            var27 = -0.002462589;
                        } else {
                            var27 = 0.002899461;
                        }
                    } else {
                        if (input[3] < 4.35) {
                            var27 = 0.0037576817;
                        } else {
                            var27 = 0.010285138;
                        }
                    }
                }
            }
        }
        double var28;
        if (input[3] < 4.4) {
            if (input[2] < 9.0) {
                if (input[0] < 30.0) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var28 = -0.00013703921;
                        } else {
                            var28 = -0.00176652;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var28 = -0.0013723079;
                        } else {
                            var28 = 0.0008565743;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[1] < 2.0) {
                            var28 = 0.0004138994;
                        } else {
                            var28 = -0.0013196631;
                        }
                    } else {
                        if (input[3] < 1.43) {
                            var28 = -0.0018216918;
                        } else {
                            var28 = -0.00014285072;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.0) {
                        if (input[0] < 91.0) {
                            var28 = -0.0004883679;
                        } else {
                            var28 = -0.0018130125;
                        }
                    } else {
                        if (input[3] < 4.3) {
                            var28 = 0.0026982292;
                        } else {
                            var28 = -0.00022915276;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.78) {
                            var28 = 0.0010949124;
                        } else {
                            var28 = -0.002115935;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var28 = 0.00078638;
                        } else {
                            var28 = 0.0056031873;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 6.0) {
                if (input[1] < 3.0) {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var28 = -0.0003084604;
                        } else {
                            var28 = 0.0001600559;
                        }
                    } else {
                        if (input[3] < 4.96) {
                            var28 = 0.0026860586;
                        } else {
                            var28 = 0.0056774993;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 4.0) {
                            var28 = 0.000027425782;
                        } else {
                            var28 = 0.0027719398;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var28 = 0.0005364722;
                        } else {
                            var28 = -0.002538557;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 75.0) {
                        if (input[0] < 20.0) {
                            var28 = 0.004507218;
                        } else {
                            var28 = 0.0004918448;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var28 = -0.0055799843;
                        } else {
                            var28 = -0.0022431107;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var28 = -0.005308322;
                        } else {
                            var28 = 0.0015437194;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var28 = 0.0050506704;
                        } else {
                            var28 = 0.009719229;
                        }
                    }
                }
            }
        }
        return subroutine2(input) + var0 + var1 + var2 + var3 + var4 + var5 + var6 + var7 + var8 + var9 + var10 + var11 + var12 + var13 + var14 + var15 + var16 + var17 + var18 + var19 + var20 + var21 + var22 + var23 + var24 + var25 + var26 + var27 + var28;
    }
    public static double subroutine2(double[] input) {
        double var0;
        if (input[3] < 2.71) {
            if (input[3] < 1.94) {
                if (input[2] < 4.0) {
                    var0 = -0.062034424;
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.53) {
                            var0 = -0.059241414;
                        } else {
                            var0 = -0.04436979;
                        }
                    } else {
                        if (input[3] < 1.67) {
                            var0 = -0.035677344;
                        } else {
                            var0 = -0.016370408;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.36) {
                        var0 = -0.054015584;
                    } else {
                        var0 = -0.036697216;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.35) {
                            var0 = -0.043223057;
                        } else {
                            var0 = -0.02551294;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var0 = -0.027622968;
                        } else {
                            var0 = 0.002490932;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var0 = -0.038131163;
                } else {
                    var0 = -0.013474104;
                }
            } else {
                if (input[3] < 3.62) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.16) {
                            var0 = -0.016565606;
                        } else {
                            var0 = 0.0056302273;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var0 = 0.00902684;
                        } else {
                            var0 = 0.04033565;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var0 = 0.03605715;
                    } else {
                        if (input[1] < 2.0) {
                            var0 = 0.049529415;
                        } else {
                            var0 = 0.08324074;
                        }
                    }
                }
            }
        }
        double var1;
        if (input[3] < 2.71) {
            if (input[3] < 1.94) {
                if (input[2] < 4.0) {
                    var1 = -0.05894492;
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var1 = -0.059957128;
                        } else {
                            var1 = -0.04852114;
                        }
                    } else {
                        if (input[3] < 1.42) {
                            var1 = -0.04359175;
                        } else {
                            var1 = -0.026591534;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.35) {
                        var1 = -0.05158005;
                    } else {
                        var1 = -0.035153516;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.35) {
                            var1 = -0.041083727;
                        } else {
                            var1 = -0.024254302;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var1 = -0.035937753;
                        } else {
                            var1 = 0.00040727347;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var1 = -0.036233854;
                } else {
                    var1 = -0.012803453;
                }
            } else {
                if (input[3] < 3.73) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.24) {
                            var1 = -0.013443777;
                        } else {
                            var1 = 0.010142452;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var1 = 0.010810607;
                        } else {
                            var1 = 0.04016646;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var1 = 0.0344274;
                    } else {
                        if (input[1] < 2.0) {
                            var1 = 0.049558733;
                        } else {
                            var1 = 0.081365615;
                        }
                    }
                }
            }
        }
        double var2;
        if (input[3] < 2.69) {
            if (input[3] < 1.94) {
                if (input[2] < 4.0) {
                    var2 = -0.056009274;
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.53) {
                            var2 = -0.05380199;
                        } else {
                            var2 = -0.04002766;
                        }
                    } else {
                        if (input[3] < 1.49) {
                            var2 = -0.034525324;
                        } else {
                            var2 = -0.019209195;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.39) {
                        var2 = -0.048432957;
                    } else {
                        var2 = -0.03284084;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.26) {
                            var2 = -0.040933847;
                        } else {
                            var2 = -0.025295159;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var2 = -0.034163047;
                        } else {
                            var2 = -0.00019132449;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var2 = -0.034435775;
                } else {
                    var2 = -0.012171409;
                }
            } else {
                if (input[3] < 3.62) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.11) {
                            var2 = -0.016816683;
                        } else {
                            var2 = 0.0041503417;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var2 = 0.0074356794;
                        } else {
                            var2 = 0.036055107;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var2 = 0.032571107;
                    } else {
                        if (input[3] < 4.0) {
                            var2 = 0.04896181;
                        } else {
                            var2 = 0.07673395;
                        }
                    }
                }
            }
        }
        double var3;
        if (input[3] < 2.66) {
            if (input[3] < 1.94) {
                if (input[2] < 4.0) {
                    var3 = -0.053219832;
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var3 = -0.05458491;
                        } else {
                            var3 = -0.04377724;
                        }
                    } else {
                        if (input[3] < 1.39) {
                            var3 = -0.039913576;
                        } else {
                            var3 = -0.02411903;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.33) {
                        var3 = -0.046990868;
                    } else {
                        var3 = -0.032963753;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.26) {
                            var3 = -0.038913395;
                        } else {
                            var3 = -0.024657942;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var3 = -0.024116669;
                        } else {
                            var3 = 0.00095333206;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var3 = -0.03272901;
                } else {
                    var3 = -0.011640332;
                }
            } else {
                if (input[3] < 3.62) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.13) {
                            var3 = -0.016122935;
                        } else {
                            var3 = 0.0045203445;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var3 = 0.006646577;
                        } else {
                            var3 = 0.033816155;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var3 = 0.03094863;
                    } else {
                        if (input[1] < 2.0) {
                            var3 = 0.04144631;
                        } else {
                            var3 = 0.071809076;
                        }
                    }
                }
            }
        }
        double var4;
        if (input[3] < 2.75) {
            if (input[3] < 1.94) {
                if (input[2] < 4.0) {
                    var4 = -0.050569322;
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.53) {
                            var4 = -0.04887038;
                        } else {
                            var4 = -0.036101487;
                        }
                    } else {
                        if (input[3] < 1.67) {
                            var4 = -0.028955424;
                        } else {
                            var4 = -0.012161134;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.42) {
                        if (input[3] < 2.22) {
                            var4 = -0.047504187;
                        } else {
                            var4 = -0.037874904;
                        }
                    } else {
                        var4 = -0.02796024;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.35) {
                            var4 = -0.035415307;
                        } else {
                            var4 = -0.02040315;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var4 = -0.02343926;
                        } else {
                            var4 = 0.0032711276;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var4 = -0.031083688;
                } else {
                    var4 = -0.010896128;
                }
            } else {
                if (input[3] < 3.86) {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.38) {
                            var4 = 0.00002977353;
                        } else {
                            var4 = 0.021145834;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var4 = 0.0028439302;
                        } else {
                            var4 = 0.038146507;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var4 = 0.02980758;
                    } else {
                        if (input[1] < 2.0) {
                            var4 = 0.046416443;
                        } else {
                            var4 = 0.07229596;
                        }
                    }
                }
            }
        }
        double var5;
        if (input[3] < 2.75) {
            if (input[3] < 1.94) {
                if (input[2] < 4.0) {
                    var5 = -0.04805081;
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.53) {
                            var5 = -0.04644253;
                        } else {
                            var5 = -0.03431062;
                        }
                    } else {
                        if (input[3] < 1.67) {
                            var5 = -0.027518703;
                        } else {
                            var5 = -0.011567913;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.43) {
                        if (input[3] < 2.2) {
                            var5 = -0.045405835;
                        } else {
                            var5 = -0.03617836;
                        }
                    } else {
                        var5 = -0.02644758;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.42) {
                            var5 = -0.0328002;
                        } else {
                            var5 = -0.01822452;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var5 = -0.030167906;
                        } else {
                            var5 = 0.0014534971;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var5 = -0.02953732;
                } else {
                    var5 = -0.010353817;
                }
            } else {
                if (input[3] < 3.86) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.32) {
                            var5 = -0.009420791;
                        } else {
                            var5 = 0.011893353;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var5 = 0.011768994;
                        } else {
                            var5 = 0.036244716;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var5 = 0.028324071;
                    } else {
                        if (input[1] < 3.0) {
                            var5 = 0.050478246;
                        } else {
                            var5 = 0.07125311;
                        }
                    }
                }
            }
        }
        double var6;
        if (input[3] < 2.82) {
            if (input[3] < 2.11) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.83) {
                        var6 = -0.04620814;
                    } else {
                        if (input[0] < 23.0) {
                            var6 = -0.030565709;
                        } else {
                            var6 = -0.042339727;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var6 = -0.04752281;
                        } else {
                            var6 = -0.035955478;
                        }
                    } else {
                        if (input[3] < 1.72) {
                            var6 = -0.029320626;
                        } else {
                            var6 = -0.01102417;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.49) {
                        var6 = -0.035188958;
                    } else {
                        var6 = -0.023297412;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 50.0) {
                            var6 = -0.016006662;
                        } else {
                            var6 = -0.027997985;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var6 = -0.020773454;
                        } else {
                            var6 = 0.008826547;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var6 = -0.028064227;
                } else {
                    var6 = -0.009833142;
                }
            } else {
                if (input[3] < 3.93) {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.38) {
                            var6 = 0.0010341054;
                        } else {
                            var6 = 0.019925943;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var6 = 0.0048133205;
                        } else {
                            var6 = 0.036876682;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var6 = 0.026924947;
                    } else {
                        if (input[2] < 4.0) {
                            var6 = 0.048516;
                        } else {
                            var6 = 0.06873428;
                        }
                    }
                }
            }
        }
        double var7;
        if (input[3] < 2.66) {
            if (input[3] < 1.94) {
                if (input[2] < 4.0) {
                    var7 = -0.043393936;
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.53) {
                            var7 = -0.0422435;
                        } else {
                            var7 = -0.030936945;
                        }
                    } else {
                        if (input[3] < 1.3) {
                            var7 = -0.030414958;
                        } else {
                            var7 = -0.016727153;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.29) {
                        var7 = -0.039231304;
                    } else {
                        if (input[3] < 2.55) {
                            var7 = -0.029658098;
                        } else {
                            var7 = -0.021713473;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 46.0) {
                            var7 = -0.019289957;
                        } else {
                            var7 = -0.031622674;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var7 = -0.027717067;
                        } else {
                            var7 = -0.00076748553;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var7 = -0.02674204;
                } else {
                    var7 = -0.0094538955;
                }
            } else {
                if (input[3] < 3.45) {
                    if (input[1] < 3.0) {
                        if (input[3] < 3.05) {
                            var7 = -0.008386513;
                        } else {
                            var7 = 0.008529662;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var7 = 0.002691463;
                        } else {
                            var7 = 0.029265031;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 3.58) {
                            var7 = 0.01954924;
                        } else {
                            var7 = 0.025193322;
                        }
                    } else {
                        if (input[3] < 4.21) {
                            var7 = 0.03973268;
                        } else {
                            var7 = 0.062997304;
                        }
                    }
                }
            }
        }
        double var8;
        if (input[3] < 2.82) {
            if (input[3] < 2.11) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.83) {
                        var8 = -0.041748125;
                    } else {
                        if (input[0] < 23.0) {
                            var8 = -0.027752329;
                        } else {
                            var8 = -0.03846167;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var8 = -0.04327731;
                        } else {
                            var8 = -0.032600414;
                        }
                    } else {
                        if (input[3] < 1.67) {
                            var8 = -0.027176915;
                        } else {
                            var8 = -0.011007066;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.49) {
                        var8 = -0.0317537;
                    } else {
                        var8 = -0.02126435;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.52) {
                            var8 = -0.024535706;
                        } else {
                            var8 = -0.013537024;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var8 = -0.01894352;
                        } else {
                            var8 = 0.008152354;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var8 = -0.025338043;
                } else {
                    var8 = -0.008873293;
                }
            } else {
                if (input[3] < 3.93) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.36) {
                            var8 = -0.0071984264;
                        } else {
                            var8 = 0.0102755455;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var8 = 0.0119366;
                        } else {
                            var8 = 0.03362168;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var8 = 0.024331514;
                    } else {
                        if (input[1] < 2.0) {
                            var8 = 0.03795573;
                        } else {
                            var8 = 0.06075294;
                        }
                    }
                }
            }
        }
        double var9;
        if (input[3] < 2.82) {
            if (input[3] < 2.25) {
                if (input[2] < 4.0) {
                    if (input[3] < 2.0) {
                        var9 = -0.03895655;
                    } else {
                        if (input[0] < 23.0) {
                            var9 = -0.0208966;
                        } else {
                            var9 = -0.032804683;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var9 = -0.04017292;
                        } else {
                            var9 = -0.0274966;
                        }
                    } else {
                        if (input[3] < 1.72) {
                            var9 = -0.021834526;
                        } else {
                            var9 = -0.003973358;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.55) {
                        var9 = -0.027553266;
                    } else {
                        var9 = -0.019473754;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 69.0) {
                            var9 = -0.013408852;
                        } else {
                            var9 = -0.02339835;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var9 = -0.01839812;
                        } else {
                            var9 = 0.0101078665;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var9 = -0.024077669;
                } else {
                    var9 = -0.008431706;
                }
            } else {
                if (input[3] < 3.73) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.26) {
                            var9 = -0.007884652;
                        } else {
                            var9 = 0.0060209134;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var9 = 0.009184628;
                        } else {
                            var9 = 0.029398367;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var9 = 0.02295221;
                    } else {
                        if (input[1] < 3.0) {
                            var9 = 0.03720978;
                        } else {
                            var9 = 0.058090348;
                        }
                    }
                }
            }
        }
        double var10;
        if (input[3] < 2.63) {
            if (input[3] < 1.94) {
                if (input[2] < 4.0) {
                    var10 = -0.037246134;
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var10 = -0.0392725;
                        } else {
                            var10 = -0.030890781;
                        }
                    } else {
                        if (input[3] < 1.33) {
                            var10 = -0.03049141;
                        } else {
                            var10 = -0.016611716;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.28) {
                            var10 = -0.034161244;
                        } else {
                            var10 = -0.024564175;
                        }
                    } else {
                        if (input[3] < 2.35) {
                            var10 = -0.022643512;
                        } else {
                            var10 = -0.009729299;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 46.0) {
                            var10 = -0.016132258;
                        } else {
                            var10 = -0.026901042;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var10 = -0.024629684;
                        } else {
                            var10 = 0.0035446007;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var10 = -0.023014138;
                } else {
                    var10 = -0.008107346;
                }
            } else {
                if (input[3] < 3.42) {
                    if (input[1] < 3.0) {
                        if (input[3] < 3.08) {
                            var10 = -0.007696993;
                        } else {
                            var10 = 0.0071716155;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var10 = 0.00035809304;
                        } else {
                            var10 = 0.024681408;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 3.58) {
                            var10 = 0.016016468;
                        } else {
                            var10 = 0.02161186;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var10 = 0.029435217;
                        } else {
                            var10 = 0.051503837;
                        }
                    }
                }
            }
        }
        double var11;
        if (input[3] < 2.82) {
            if (input[3] < 2.11) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.77) {
                        var11 = -0.036038976;
                    } else {
                        if (input[0] < 23.0) {
                            var11 = -0.02358247;
                        } else {
                            var11 = -0.033482894;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.53) {
                            var11 = -0.035224695;
                        } else {
                            var11 = -0.024540398;
                        }
                    } else {
                        if (input[3] < 1.72) {
                            var11 = -0.019636469;
                        } else {
                            var11 = -0.0049073915;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.47) {
                        var11 = -0.027503697;
                    } else {
                        var11 = -0.018734412;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 51.0) {
                            var11 = -0.012071378;
                        } else {
                            var11 = -0.022149196;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var11 = -0.016400866;
                        } else {
                            var11 = 0.007249014;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var11 = -0.021735216;
                } else {
                    var11 = -0.007608615;
                }
            } else {
                if (input[3] < 3.93) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.29) {
                            var11 = -0.0073096897;
                        } else {
                            var11 = 0.006949653;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var11 = 0.009976783;
                        } else {
                            var11 = 0.028880421;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var11 = 0.020903205;
                    } else {
                        if (input[2] < 4.0) {
                            var11 = 0.036611486;
                        } else {
                            var11 = 0.054555632;
                        }
                    }
                }
            }
        }
        double var12;
        if (input[3] < 2.98) {
            if (input[3] < 2.26) {
                if (input[2] < 4.0) {
                    if (input[3] < 2.0) {
                        var12 = -0.033465333;
                    } else {
                        if (input[0] < 42.0) {
                            var12 = -0.019958496;
                        } else {
                            var12 = -0.029156446;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.53) {
                            var12 = -0.03347475;
                        } else {
                            var12 = -0.022812497;
                        }
                    } else {
                        if (input[3] < 1.72) {
                            var12 = -0.018661706;
                        } else {
                            var12 = -0.003109569;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.56) {
                        var12 = -0.023466593;
                    } else {
                        if (input[3] < 2.9) {
                            var12 = -0.016597042;
                        } else {
                            var12 = -0.009988095;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 42.0) {
                            var12 = -0.008789619;
                        } else {
                            var12 = -0.017316936;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var12 = -0.015619181;
                        } else {
                            var12 = 0.009733434;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var12 = -0.020647002;
                } else {
                    var12 = -0.007104575;
                }
            } else {
                if (input[3] < 3.99) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.5) {
                            var12 = -0.0016273413;
                        } else {
                            var12 = 0.00925362;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var12 = 0.01222245;
                        } else {
                            var12 = 0.030280475;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var12 = 0.019879779;
                    } else {
                        if (input[1] < 2.0) {
                            var12 = 0.030705234;
                        } else {
                            var12 = 0.050902706;
                        }
                    }
                }
            }
        }
        double var13;
        if (input[3] < 2.98) {
            if (input[3] < 2.26) {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.14) {
                            var13 = -0.03383559;
                        } else {
                            var13 = -0.026435161;
                        }
                    } else {
                        if (input[3] < 1.7) {
                            var13 = -0.031055396;
                        } else {
                            var13 = -0.02060212;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.82) {
                            var13 = -0.03238998;
                        } else {
                            var13 = -0.02449814;
                        }
                    } else {
                        if (input[3] < 1.67) {
                            var13 = -0.021191185;
                        } else {
                            var13 = -0.006559571;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.56) {
                            var13 = -0.022314597;
                        } else {
                            var13 = -0.01464334;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var13 = -0.020566264;
                        } else {
                            var13 = -0.0059843496;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 61.0) {
                            var13 = -0.010015567;
                        } else {
                            var13 = -0.018444525;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var13 = -0.014161839;
                        } else {
                            var13 = 0.014016672;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var13 = -0.01962032;
                } else {
                    var13 = -0.0067510926;
                }
            } else {
                if (input[3] < 4.04) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.53) {
                            var13 = -0.0013667567;
                        } else {
                            var13 = 0.009360224;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var13 = 0.0116893565;
                        } else {
                            var13 = 0.029260615;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var13 = 0.018885734;
                    } else {
                        if (input[1] < 2.0) {
                            var13 = 0.030379474;
                        } else {
                            var13 = 0.049166653;
                        }
                    }
                }
            }
        }
        double var14;
        if (input[3] < 3.02) {
            if (input[3] < 2.26) {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.16) {
                            var14 = -0.032095093;
                        } else {
                            var14 = -0.024696931;
                        }
                    } else {
                        if (input[3] < 1.7) {
                            var14 = -0.029518152;
                        } else {
                            var14 = -0.019583208;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.82) {
                            var14 = -0.03078313;
                        } else {
                            var14 = -0.023289142;
                        }
                    } else {
                        if (input[3] < 1.72) {
                            var14 = -0.019584863;
                        } else {
                            var14 = -0.0054259836;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var14 = -0.020888992;
                    } else {
                        if (input[1] < 2.0) {
                            var14 = -0.015719553;
                        } else {
                            var14 = -0.007065933;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var14 = -0.01627761;
                        } else {
                            var14 = -0.0055128364;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var14 = -0.0102959275;
                        } else {
                            var14 = 0.013247984;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var14 = -0.01864297;
                } else {
                    var14 = -0.006409143;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.47) {
                        if (input[1] < 2.0) {
                            var14 = -0.00088825665;
                        } else {
                            var14 = 0.010831895;
                        }
                    } else {
                        if (input[3] < 3.67) {
                            var14 = 0.013846722;
                        } else {
                            var14 = 0.017699841;
                        }
                    }
                } else {
                    if (input[3] < 4.21) {
                        if (input[1] < 4.0) {
                            var14 = 0.014363772;
                        } else {
                            var14 = 0.036212202;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var14 = 0.037920725;
                        } else {
                            var14 = 0.05442083;
                        }
                    }
                }
            }
        }
        double var15;
        if (input[3] < 2.55) {
            if (input[3] < 1.85) {
                if (input[2] < 4.0) {
                    var15 = -0.029224738;
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.34) {
                            var15 = -0.031102354;
                        } else {
                            var15 = -0.0223001;
                        }
                    } else {
                        if (input[3] < 1.22) {
                            var15 = -0.02310235;
                        } else {
                            var15 = -0.011760425;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.18) {
                        var15 = -0.030145094;
                    } else {
                        var15 = -0.021426534;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 59.0) {
                            var15 = -0.015518961;
                        } else {
                            var15 = -0.025586242;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var15 = -0.011189829;
                        } else {
                            var15 = 0.002605777;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var15 = -0.018080618;
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.91) {
                            var15 = -0.012432034;
                        } else {
                            var15 = -0.0060389317;
                        }
                    } else {
                        if (input[3] < 2.68) {
                            var15 = -0.003961302;
                        } else {
                            var15 = -0.006067742;
                        }
                    }
                }
            } else {
                if (input[3] < 3.41) {
                    if (input[1] < 3.0) {
                        if (input[3] < 3.13) {
                            var15 = -0.006252675;
                        } else {
                            var15 = 0.005381336;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var15 = -0.001184897;
                        } else {
                            var15 = 0.018743992;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 3.67) {
                            var15 = 0.012211171;
                        } else {
                            var15 = 0.016818222;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var15 = 0.021741424;
                        } else {
                            var15 = 0.04112768;
                        }
                    }
                }
            }
        }
        double var16;
        if (input[3] < 3.02) {
            if (input[3] < 2.26) {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.14) {
                            var16 = -0.029156683;
                        } else {
                            var16 = -0.022803335;
                        }
                    } else {
                        if (input[3] < 1.4) {
                            var16 = -0.029278431;
                        } else {
                            var16 = -0.019342735;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.82) {
                            var16 = -0.027806176;
                        } else {
                            var16 = -0.021122064;
                        }
                    } else {
                        if (input[3] < 1.48) {
                            var16 = -0.020454895;
                        } else {
                            var16 = -0.007531258;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var16 = -0.019044703;
                    } else {
                        if (input[1] < 2.0) {
                            var16 = -0.0142527865;
                        } else {
                            var16 = -0.006351354;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var16 = -0.014872161;
                        } else {
                            var16 = -0.0032777668;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var16 = -0.00834783;
                        } else {
                            var16 = 0.017744685;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var16 = -0.016817046;
                } else {
                    var16 = -0.0057886713;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.47) {
                        if (input[1] < 2.0) {
                            var16 = -0.0010684836;
                        } else {
                            var16 = 0.009762584;
                        }
                    } else {
                        if (input[3] < 3.82) {
                            var16 = 0.013634294;
                        } else {
                            var16 = 0.016212197;
                        }
                    }
                } else {
                    if (input[3] < 4.21) {
                        if (input[1] < 4.0) {
                            var16 = 0.0124616055;
                        } else {
                            var16 = 0.033016913;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var16 = 0.034275528;
                        } else {
                            var16 = 0.050061595;
                        }
                    }
                }
            }
        }
        double var17;
        if (input[3] < 2.55) {
            if (input[3] < 1.77) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.33) {
                        var17 = -0.027293134;
                    } else {
                        if (input[1] < 2.0) {
                            var17 = -0.027697349;
                        } else {
                            var17 = -0.019183623;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        var17 = -0.025830476;
                    } else {
                        if (input[3] < 1.28) {
                            var17 = -0.01617162;
                        } else {
                            var17 = -0.0057818457;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 2.17) {
                        var17 = -0.027908683;
                    } else {
                        var17 = -0.019611325;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 59.0) {
                            var17 = -0.014504005;
                        } else {
                            var17 = -0.024071267;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var17 = -0.020755392;
                        } else {
                            var17 = -0.0019465482;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var17 = -0.01632158;
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.91) {
                            var17 = -0.011231217;
                        } else {
                            var17 = -0.0054120263;
                        }
                    } else {
                        if (input[3] < 2.68) {
                            var17 = -0.0034800388;
                        } else {
                            var17 = -0.005475084;
                        }
                    }
                }
            } else {
                if (input[3] < 3.41) {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var17 = -0.0075111142;
                        } else {
                            var17 = 0.0026324347;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var17 = -0.0013913976;
                        } else {
                            var17 = 0.017072028;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 3.67) {
                            var17 = 0.01105695;
                        } else {
                            var17 = 0.015191202;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var17 = 0.01911654;
                        } else {
                            var17 = 0.03753452;
                        }
                    }
                }
            }
        }
        double var18;
        if (input[3] < 3.03) {
            if (input[3] < 2.25) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.51) {
                        if (input[3] < 1.33) {
                            var18 = -0.025934918;
                        } else {
                            var18 = -0.021629335;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var18 = -0.02607117;
                        } else {
                            var18 = -0.01512682;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.08) {
                            var18 = -0.023275588;
                        } else {
                            var18 = -0.014238292;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var18 = -0.019477967;
                        } else {
                            var18 = -0.0018941194;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    var18 = -0.017226461;
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var18 = -0.013592796;
                        } else {
                            var18 = -0.0034337745;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var18 = -0.00818307;
                        } else {
                            var18 = 0.013643138;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var18 = -0.015168409;
                } else {
                    var18 = -0.005228374;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.47) {
                        if (input[1] < 2.0) {
                            var18 = -0.00090639153;
                        } else {
                            var18 = 0.008854718;
                        }
                    } else {
                        if (input[3] < 3.82) {
                            var18 = 0.01230811;
                        } else {
                            var18 = 0.014648998;
                        }
                    }
                } else {
                    if (input[3] < 4.21) {
                        if (input[2] < 4.0) {
                            var18 = 0.0059633083;
                        } else {
                            var18 = 0.026089406;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var18 = 0.030975861;
                        } else {
                            var18 = 0.046077814;
                        }
                    }
                }
            }
        }
        double var19;
        if (input[3] < 2.55) {
            if (input[3] < 1.83) {
                if (input[2] < 4.0) {
                    var19 = -0.024163574;
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.31) {
                            var19 = -0.026036218;
                        } else {
                            var19 = -0.018771484;
                        }
                    } else {
                        if (input[3] < 1.22) {
                            var19 = -0.019152952;
                        } else {
                            var19 = -0.00914527;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.16) {
                            var19 = -0.02487861;
                        } else {
                            var19 = -0.017835706;
                        }
                    } else {
                        if (input[0] < 38.0) {
                            var19 = -0.009448255;
                        } else {
                            var19 = -0.015983358;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 59.0) {
                            var19 = -0.012850537;
                        } else {
                            var19 = -0.02131622;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var19 = -0.020157268;
                        } else {
                            var19 = 0.001946942;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var19 = -0.014732917;
                } else {
                    if (input[3] < 2.59) {
                        if (input[1] < 2.0) {
                            var19 = -0.010128652;
                        } else {
                            var19 = -0.0021024586;
                        }
                    } else {
                        var19 = -0.005038935;
                    }
                }
            } else {
                if (input[3] < 3.33) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var19 = -0.007944009;
                        } else {
                            var19 = 0.0023954026;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var19 = -0.0025800546;
                        } else {
                            var19 = 0.019311212;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 3.59) {
                            var19 = 0.008953436;
                        } else {
                            var19 = 0.013635744;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var19 = 0.016997002;
                        } else {
                            var19 = 0.034640387;
                        }
                    }
                }
            }
        }
        double var20;
        if (input[3] < 2.98) {
            if (input[3] < 2.11) {
                if (input[2] < 4.0) {
                    if (input[0] < 10.0) {
                        if (input[3] < 1.57) {
                            var20 = -0.021591587;
                        } else {
                            var20 = -0.014535949;
                        }
                    } else {
                        var20 = -0.022875747;
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var20 = -0.024524614;
                        } else {
                            var20 = -0.01654396;
                        }
                    } else {
                        if (input[3] < 1.3) {
                            var20 = -0.016635641;
                        } else {
                            var20 = -0.0060273213;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 2.52) {
                        if (input[1] < 2.0) {
                            var20 = -0.01744597;
                        } else {
                            var20 = -0.010701986;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var20 = -0.014707826;
                        } else {
                            var20 = -0.003997935;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 61.0) {
                            var20 = -0.007321493;
                        } else {
                            var20 = -0.015093899;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var20 = -0.011102339;
                        } else {
                            var20 = 0.013542744;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var20 = -0.013677408;
                } else {
                    var20 = -0.004723259;
                }
            } else {
                if (input[3] < 4.04) {
                    if (input[1] < 2.0) {
                        if (input[3] < 3.7) {
                            var20 = -0.00198961;
                        } else {
                            var20 = 0.0042077727;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var20 = 0.0068936395;
                        } else {
                            var20 = 0.0214089;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        var20 = 0.013261043;
                    } else {
                        if (input[1] < 2.0) {
                            var20 = 0.019515038;
                        } else {
                            var20 = 0.03606417;
                        }
                    }
                }
            }
        }
        double var21;
        if (input[3] < 3.05) {
            if (input[3] < 2.33) {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.1) {
                            var21 = -0.022861643;
                        } else {
                            var21 = -0.01805077;
                        }
                    } else {
                        if (input[3] < 1.7) {
                            var21 = -0.021224467;
                        } else {
                            var21 = -0.013604145;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.91) {
                            var21 = -0.021477772;
                        } else {
                            var21 = -0.015901536;
                        }
                    } else {
                        if (input[3] < 1.63) {
                            var21 = -0.014482505;
                        } else {
                            var21 = -0.0035700449;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    var21 = -0.014736203;
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var21 = -0.010977135;
                        } else {
                            var21 = -0.0031288478;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var21 = -0.0067858;
                        } else {
                            var21 = 0.009024159;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var21 = -0.013001362;
                } else {
                    var21 = -0.0044826115;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.43) {
                        if (input[1] < 2.0) {
                            var21 = -0.0009459678;
                        } else {
                            var21 = 0.006748352;
                        }
                    } else {
                        if (input[3] < 3.77) {
                            var21 = 0.010028406;
                        } else {
                            var21 = 0.012519899;
                        }
                    }
                } else {
                    if (input[3] < 4.21) {
                        if (input[2] < 4.0) {
                            var21 = 0.004303302;
                        } else {
                            var21 = 0.022908721;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var21 = 0.02647478;
                        } else {
                            var21 = 0.04069217;
                        }
                    }
                }
            }
        }
        double var22;
        if (input[3] < 3.03) {
            if (input[3] < 2.33) {
                if (input[1] < 4.0) {
                    if (input[3] < 1.57) {
                        if (input[3] < 1.31) {
                            var22 = -0.02205374;
                        } else {
                            var22 = -0.018784828;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var22 = -0.020745821;
                        } else {
                            var22 = -0.012400906;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.85) {
                            var22 = -0.020584047;
                        } else {
                            var22 = -0.015228666;
                        }
                    } else {
                        if (input[3] < 1.77) {
                            var22 = -0.0106603345;
                        } else {
                            var22 = 0.00038827173;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var22 = -0.014032234;
                    } else {
                        if (input[1] < 2.0) {
                            var22 = -0.009813928;
                        } else {
                            var22 = -0.0044815275;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var22 = -0.01050247;
                        } else {
                            var22 = -0.001547313;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var22 = -0.005438803;
                        } else {
                            var22 = 0.014399222;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var22 = -0.012354898;
                } else {
                    var22 = -0.0042632646;
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[3] < 4.31) {
                        if (input[1] < 2.0) {
                            var22 = 0.0013788481;
                        } else {
                            var22 = 0.0109889405;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var22 = 0.011834905;
                        } else {
                            var22 = 0.026486168;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 3.41) {
                            var22 = 0.005624918;
                        } else {
                            var22 = 0.011848713;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var22 = 0.011928416;
                        } else {
                            var22 = 0.0356771;
                        }
                    }
                }
            }
        }
        double var23;
        if (input[3] < 2.55) {
            if (input[3] < 1.77) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.33) {
                        var23 = -0.020414583;
                    } else {
                        if (input[1] < 2.0) {
                            var23 = -0.020869397;
                        } else {
                            var23 = -0.014186372;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        var23 = -0.01913535;
                    } else {
                        if (input[3] < 1.3) {
                            var23 = -0.011689599;
                        } else {
                            var23 = -0.0029283017;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.1) {
                            var23 = -0.02151756;
                        } else {
                            var23 = -0.014977767;
                        }
                    } else {
                        if (input[0] < 52.0) {
                            var23 = -0.008287061;
                        } else {
                            var23 = -0.014395204;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 45.0) {
                            var23 = -0.009294241;
                        } else {
                            var23 = -0.01731063;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var23 = -0.018512495;
                        } else {
                            var23 = 0.0018179872;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var23 = -0.012026802;
                } else {
                    if (input[3] < 2.59) {
                        if (input[1] < 2.0) {
                            var23 = -0.008819242;
                        } else {
                            var23 = -0.001885903;
                        }
                    } else {
                        var23 = -0.004119651;
                    }
                }
            } else {
                if (input[3] < 3.54) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var23 = -0.0056824447;
                        } else {
                            var23 = 0.0033791147;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var23 = -0.00013435827;
                        } else {
                            var23 = 0.018116912;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[2] < 4.0) {
                            var23 = 0.012075047;
                        } else {
                            var23 = 0.010368346;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var23 = 0.009945076;
                        } else {
                            var23 = 0.028341591;
                        }
                    }
                }
            }
        }
        double var24;
        if (input[3] < 3.05) {
            if (input[3] < 2.11) {
                if (input[2] < 4.0) {
                    if (input[0] < 10.0) {
                        if (input[3] < 1.57) {
                            var24 = -0.017672572;
                        } else {
                            var24 = -0.011600008;
                        }
                    } else {
                        var24 = -0.018897725;
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.34) {
                            var24 = -0.020375822;
                        } else {
                            var24 = -0.013538842;
                        }
                    } else {
                        if (input[3] < 1.3) {
                            var24 = -0.0138254585;
                        } else {
                            var24 = -0.0046744593;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[3] < 2.56) {
                        if (input[1] < 2.0) {
                            var24 = -0.014116587;
                        } else {
                            var24 = -0.009620478;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var24 = -0.008263591;
                        } else {
                            var24 = -0.002675219;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 69.0) {
                            var24 = -0.005992749;
                        } else {
                            var24 = -0.013872705;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var24 = -0.008748991;
                        } else {
                            var24 = 0.007899465;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var24 = -0.011142834;
                } else {
                    var24 = -0.0038426963;
                }
            } else {
                if (input[3] < 4.22) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var24 = 0.00080965407;
                        } else {
                            var24 = 0.010283849;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var24 = 0.0053805993;
                        } else {
                            var24 = 0.025686605;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var24 = 0.010835345;
                        } else {
                            var24 = 0.022633398;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var24 = 0.01977246;
                        } else {
                            var24 = 0.046990078;
                        }
                    }
                }
            }
        }
        double var25;
        if (input[3] < 3.16) {
            if (input[3] < 2.33) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.53) {
                        if (input[3] < 1.31) {
                            var25 = -0.018573146;
                        } else {
                            var25 = -0.015535908;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var25 = -0.018150376;
                        } else {
                            var25 = -0.010060928;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 2.08) {
                            var25 = -0.016379608;
                        } else {
                            var25 = -0.01057156;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var25 = -0.015473629;
                        } else {
                            var25 = 0.00014488307;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    var25 = -0.012018659;
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var25 = -0.008650521;
                        } else {
                            var25 = -0.0016539805;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var25 = -0.0045747207;
                        } else {
                            var25 = 0.01077647;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var25 = -0.010584431;
                } else {
                    var25 = -0.00364986;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.47) {
                        if (input[1] < 2.0) {
                            var25 = 0.00018923123;
                        } else {
                            var25 = 0.006389312;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var25 = 0.010946126;
                        } else {
                            var25 = 0.0091379685;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.45) {
                            var25 = 0.0044397875;
                        } else {
                            var25 = 0.023996508;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var25 = 0.011725069;
                        } else {
                            var25 = 0.03252373;
                        }
                    }
                }
            }
        }
        double var26;
        if (input[3] < 3.16) {
            if (input[3] < 2.33) {
                if (input[2] < 4.0) {
                    if (input[3] < 2.11) {
                        if (input[0] < 50.0) {
                            var26 = -0.015399988;
                        } else {
                            var26 = -0.018004758;
                        }
                    } else {
                        if (input[0] < 38.0) {
                            var26 = -0.006174576;
                        } else {
                            var26 = -0.014283227;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.6) {
                            var26 = -0.016677957;
                        } else {
                            var26 = -0.010663855;
                        }
                    } else {
                        if (input[3] < 1.77) {
                            var26 = -0.008690841;
                        } else {
                            var26 = 0.00074591534;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var26 = -0.011424713;
                    } else {
                        if (input[3] < 2.59) {
                            var26 = -0.008090364;
                        } else {
                            var26 = -0.0037257546;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var26 = -0.00822151;
                        } else {
                            var26 = -0.0012895453;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var26 = -0.004263754;
                        } else {
                            var26 = 0.012621211;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var26 = -0.010058434;
                } else {
                    var26 = -0.0034683996;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.67) {
                        if (input[1] < 2.0) {
                            var26 = 0.00069810095;
                        } else {
                            var26 = 0.0068723382;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var26 = 0.010487515;
                        } else {
                            var26 = 0.009030805;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.35) {
                            var26 = 0.0032221414;
                        } else {
                            var26 = 0.020741513;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var26 = 0.011142238;
                        } else {
                            var26 = 0.030903393;
                        }
                    }
                }
            }
        }
        double var27;
        if (input[3] < 2.91) {
            if (input[3] < 2.11) {
                if (input[2] < 4.0) {
                    if (input[0] < 10.0) {
                        if (input[3] < 1.77) {
                            var27 = -0.014572183;
                        } else {
                            var27 = -0.008884914;
                        }
                    } else {
                        var27 = -0.016351085;
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var27 = -0.017754827;
                        } else {
                            var27 = -0.011450037;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var27 = -0.0153207285;
                        } else {
                            var27 = -0.004471579;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.46) {
                            var27 = -0.012769379;
                        } else {
                            var27 = -0.008673768;
                        }
                    } else {
                        if (input[0] < 51.0) {
                            var27 = -0.0019420696;
                        } else {
                            var27 = -0.008103533;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 33.0) {
                            var27 = -0.003095649;
                        } else {
                            var27 = -0.00980943;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var27 = -0.008852414;
                        } else {
                            var27 = 0.010245665;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var27 = -0.009548083;
                } else {
                    var27 = -0.0033227806;
                }
            } else {
                if (input[3] < 3.93) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var27 = -0.0014373322;
                        } else {
                            var27 = 0.0069687366;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var27 = 0.0035871912;
                        } else {
                            var27 = 0.019988192;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var27 = 0.009327217;
                        } else {
                            var27 = 0.017844368;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var27 = 0.013676844;
                        } else {
                            var27 = 0.03694738;
                        }
                    }
                }
            }
        }
        double var28;
        if (input[3] < 3.16) {
            if (input[3] < 2.33) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.51) {
                        if (input[3] < 1.31) {
                            var28 = -0.016182315;
                        } else {
                            var28 = -0.013427799;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var28 = -0.015845478;
                        } else {
                            var28 = -0.008648066;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.85) {
                            var28 = -0.014855315;
                        } else {
                            var28 = -0.010839268;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var28 = -0.013826235;
                        } else {
                            var28 = 0.00045636957;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var28 = -0.011274384;
                        } else {
                            var28 = -0.008810899;
                        }
                    } else {
                        if (input[3] < 2.59) {
                            var28 = -0.007284029;
                        } else {
                            var28 = -0.0032831577;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var28 = -0.0074511706;
                        } else {
                            var28 = -0.001167076;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var28 = -0.0038364013;
                        } else {
                            var28 = 0.011335323;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var28 = -0.009084088;
                } else {
                    var28 = -0.0031307612;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.67) {
                        if (input[1] < 2.0) {
                            var28 = 0.0007332194;
                        } else {
                            var28 = 0.006001079;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var28 = 0.009554022;
                        } else {
                            var28 = 0.008118455;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.49) {
                            var28 = 0.003943736;
                        } else {
                            var28 = 0.022267396;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var28 = 0.0098677445;
                        } else {
                            var28 = 0.02823782;
                        }
                    }
                }
            }
        }
        double var29;
        if (input[3] < 3.18) {
            if (input[3] < 2.25) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.51) {
                        if (input[3] < 1.31) {
                            var29 = -0.015377285;
                        } else {
                            var29 = -0.012761819;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var29 = -0.015656702;
                        } else {
                            var29 = -0.0086464435;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 42.0) {
                            var29 = -0.010525837;
                        } else {
                            var29 = -0.014655477;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var29 = -0.013174663;
                        } else {
                            var29 = 0.0002659047;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 2.55) {
                        if (input[1] < 2.0) {
                            var29 = -0.01032563;
                        } else {
                            var29 = -0.006872797;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var29 = -0.009331319;
                        } else {
                            var29 = -0.0020554403;
                        }
                    }
                } else {
                    if (input[2] < 5.0) {
                        if (input[4] < 1.0) {
                            var29 = -0.010188012;
                        } else {
                            var29 = -0.0033459363;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var29 = -0.006569913;
                        } else {
                            var29 = 0.010866961;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var29 = -0.008631998;
                } else {
                    var29 = -0.0029750234;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.43) {
                        if (input[1] < 2.0) {
                            var29 = 0.00011729553;
                        } else {
                            var29 = 0.004597036;
                        }
                    } else {
                        if (input[3] < 3.77) {
                            var29 = 0.0063751102;
                        } else {
                            var29 = 0.008412742;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 4.31) {
                            var29 = 0.0054306607;
                        } else {
                            var29 = 0.02103403;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var29 = 0.007881978;
                        } else {
                            var29 = 0.03003102;
                        }
                    }
                }
            }
        }
        double var30;
        if (input[3] < 3.18) {
            if (input[3] < 2.16) {
                if (input[2] < 4.0) {
                    if (input[0] < 13.0) {
                        if (input[3] < 1.77) {
                            var30 = -0.012777855;
                        } else {
                            var30 = -0.007357585;
                        }
                    } else {
                        if (input[0] < 60.0) {
                            var30 = -0.013314517;
                        } else {
                            var30 = -0.015206895;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var30 = -0.015273145;
                        } else {
                            var30 = -0.010579625;
                        }
                    } else {
                        if (input[3] < 1.21) {
                            var30 = -0.015024583;
                        } else {
                            var30 = -0.004354978;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 2.55) {
                        if (input[1] < 2.0) {
                            var30 = -0.010191683;
                        } else {
                            var30 = -0.0065585845;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var30 = -0.008878473;
                        } else {
                            var30 = -0.0019530794;
                        }
                    }
                } else {
                    if (input[2] < 5.0) {
                        if (input[0] < 70.0) {
                            var30 = -0.0028450887;
                        } else {
                            var30 = -0.009254782;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var30 = -0.007281795;
                        } else {
                            var30 = 0.010060415;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var30 = -0.008203065;
                } else {
                    var30 = -0.002827124;
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 4.53) {
                        if (input[4] < 4.0) {
                            var30 = 0.005079603;
                        } else {
                            var30 = -0.0009625095;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var30 = 0.007744204;
                        } else {
                            var30 = 0.021154081;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var30 = 0.0072959387;
                        } else {
                            var30 = 0.014317189;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var30 = 0.007669578;
                        } else {
                            var30 = 0.029176245;
                        }
                    }
                }
            }
        }
        double var31;
        if (input[3] < 2.93) {
            if (input[3] < 2.1) {
                if (input[2] < 4.0) {
                    if (input[0] < 50.0) {
                        if (input[3] < 1.74) {
                            var31 = -0.013224149;
                        } else {
                            var31 = -0.009497862;
                        }
                    } else {
                        if (input[3] < 1.62) {
                            var31 = -0.013215273;
                        } else {
                            var31 = -0.015629917;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.34) {
                            var31 = -0.014799294;
                        } else {
                            var31 = -0.009382311;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var31 = -0.006914047;
                        } else {
                            var31 = 0.0013693719;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 42.0) {
                        if (input[4] < 1.0) {
                            var31 = -0.011324688;
                        } else {
                            var31 = -0.0032558471;
                        }
                    } else {
                        if (input[0] < 79.0) {
                            var31 = -0.008326311;
                        } else {
                            var31 = -0.012588694;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var31 = -0.008980092;
                        } else {
                            var31 = -0.0029032435;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var31 = -0.0063880677;
                        } else {
                            var31 = 0.006155788;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var31 = -0.007781446;
                } else {
                    var31 = -0.0026989977;
                }
            } else {
                if (input[3] < 4.2) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var31 = -0.0008689915;
                        } else {
                            var31 = 0.006101888;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var31 = 0.0027090108;
                        } else {
                            var31 = 0.017515825;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var31 = 0.007665947;
                        } else {
                            var31 = 0.015641747;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var31 = 0.014504119;
                        } else {
                            var31 = 0.03550826;
                        }
                    }
                }
            }
        }
        double var32;
        if (input[3] < 3.24) {
            if (input[3] < 2.46) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.53) {
                        if (input[3] < 1.31) {
                            var32 = -0.013314086;
                        } else {
                            var32 = -0.011065221;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var32 = -0.012597017;
                        } else {
                            var32 = -0.0067418786;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.85) {
                            var32 = -0.012183645;
                        } else {
                            var32 = -0.008928805;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var32 = -0.011843378;
                        } else {
                            var32 = 0.0014964497;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var32 = -0.006495909;
                        } else {
                            var32 = -0.010081182;
                        }
                    } else {
                        var32 = -0.007227187;
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var32 = -0.005462752;
                        } else {
                            var32 = -0.0012599947;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var32 = -0.0027155045;
                        } else {
                            var32 = 0.005934735;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var32 = -0.007407306;
                } else {
                    var32 = -0.002551816;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.67) {
                        if (input[0] < 41.0) {
                            var32 = 0.0064207218;
                        } else {
                            var32 = 0.002707371;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var32 = 0.008023827;
                        } else {
                            var32 = 0.0065375045;
                        }
                    }
                } else {
                    if (input[3] < 4.53) {
                        if (input[1] < 2.0) {
                            var32 = -0.0009329955;
                        } else {
                            var32 = 0.014524895;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var32 = 0.017237386;
                        } else {
                            var32 = 0.033846088;
                        }
                    }
                }
            }
        }
        double var33;
        if (input[3] < 3.29) {
            if (input[3] < 2.33) {
                if (input[2] < 4.0) {
                    if (input[0] < 16.0) {
                        if (input[3] < 1.85) {
                            var33 = -0.010965558;
                        } else {
                            var33 = -0.0046643014;
                        }
                    } else {
                        if (input[3] < 2.2) {
                            var33 = -0.012395406;
                        } else {
                            var33 = -0.00909302;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.6) {
                            var33 = -0.012036677;
                        } else {
                            var33 = -0.007218823;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var33 = -0.012544624;
                        } else {
                            var33 = -0.0015196641;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var33 = -0.009091149;
                        } else {
                            var33 = -0.0068894825;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var33 = -0.0056782947;
                        } else {
                            var33 = -0.0024724007;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var33 = -0.0056605325;
                        } else {
                            var33 = -0.00038147622;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var33 = -0.0026768807;
                        } else {
                            var33 = 0.009141604;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var33 = -0.0070384205;
                } else {
                    var33 = -0.0024245332;
                }
            } else {
                if (input[4] < 4.0) {
                    if (input[3] < 3.77) {
                        if (input[0] < 41.0) {
                            var33 = 0.0065616383;
                        } else {
                            var33 = 0.002996607;
                        }
                    } else {
                        var33 = 0.006917432;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.56) {
                            var33 = 0.0029871254;
                        } else {
                            var33 = 0.019155327;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var33 = 0.007804986;
                        } else {
                            var33 = 0.024062518;
                        }
                    }
                }
            }
        }
        double var34;
        if (input[3] < 3.18) {
            if (input[3] < 2.16) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.33) {
                        if (input[1] < 2.0) {
                            var34 = -0.010428052;
                        } else {
                            var34 = -0.012562535;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var34 = -0.0130309835;
                        } else {
                            var34 = -0.0072637545;
                        }
                    }
                } else {
                    if (input[2] < 6.0) {
                        if (input[2] < 4.0) {
                            var34 = -0.010072927;
                        } else {
                            var34 = -0.0059419503;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var34 = -0.0076631927;
                        } else {
                            var34 = 0.0027534754;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 42.0) {
                        if (input[4] < 1.0) {
                            var34 = -0.008735512;
                        } else {
                            var34 = -0.0015029422;
                        }
                    } else {
                        if (input[0] < 79.0) {
                            var34 = -0.0065069194;
                        } else {
                            var34 = -0.010639699;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var34 = -0.0068667196;
                        } else {
                            var34 = -0.001942214;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var34 = -0.0055977143;
                        } else {
                            var34 = 0.0060237045;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var34 = -0.006691587;
                } else {
                    var34 = -0.0023097906;
                }
            } else {
                if (input[3] < 4.25) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var34 = -0.00055289594;
                        } else {
                            var34 = 0.0064631957;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var34 = 0.0029426108;
                        } else {
                            var34 = 0.01672779;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var34 = 0.0065831468;
                        } else {
                            var34 = 0.01358977;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var34 = 0.012345462;
                        } else {
                            var34 = 0.03352905;
                        }
                    }
                }
            }
        }
        double var35;
        if (input[3] < 2.53) {
            if (input[3] < 1.77) {
                if (input[3] < 1.33) {
                    if (input[1] < 5.0) {
                        if (input[1] < 2.0) {
                            var35 = -0.00991534;
                        } else {
                            var35 = -0.011938515;
                        }
                    } else {
                        if (input[3] < 1.22) {
                            var35 = -0.009970612;
                        } else {
                            var35 = -0.0053613717;
                        }
                    }
                } else {
                    if (input[2] < 5.0) {
                        if (input[0] < 9.0) {
                            var35 = -0.006991529;
                        } else {
                            var35 = -0.011383023;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var35 = -0.010044003;
                        } else {
                            var35 = -0.003779759;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 65.0) {
                        if (input[0] < 23.0) {
                            var35 = -0.0046278764;
                        } else {
                            var35 = -0.008155655;
                        }
                    } else {
                        if (input[3] < 2.24) {
                            var35 = -0.01386101;
                        } else {
                            var35 = -0.009458751;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.03) {
                            var35 = -0.014415654;
                        } else {
                            var35 = -0.008555673;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var35 = -0.011671404;
                        } else {
                            var35 = 0.0009311903;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        var35 = -0.008385504;
                    } else {
                        var35 = -0.006362231;
                    }
                } else {
                    if (input[3] < 2.59) {
                        if (input[1] < 2.0) {
                            var35 = -0.0058027403;
                        } else {
                            var35 = -0.0007243991;
                        }
                    } else {
                        if (input[3] < 2.64) {
                            var35 = -0.0011353153;
                        } else {
                            var35 = -0.0022327711;
                        }
                    }
                }
            } else {
                if (input[3] < 3.54) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var35 = -0.003877754;
                        } else {
                            var35 = 0.0012509156;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var35 = 0.003941448;
                        } else {
                            var35 = 0.017192645;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var35 = 0.0059823543;
                        } else {
                            var35 = 0.010889627;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var35 = 0.009355742;
                        } else {
                            var35 = 0.026782427;
                        }
                    }
                }
            }
        }
        double var36;
        if (input[3] < 3.29) {
            if (input[3] < 2.46) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.53) {
                        if (input[3] < 1.31) {
                            var36 = -0.010945466;
                        } else {
                            var36 = -0.009111841;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var36 = -0.010532665;
                        } else {
                            var36 = -0.0055268323;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 71.0) {
                            var36 = -0.00779684;
                        } else {
                            var36 = -0.010940527;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var36 = -0.007483523;
                        } else {
                            var36 = 0.0022803892;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var36 = -0.0051159314;
                        } else {
                            var36 = -0.008442479;
                        }
                    } else {
                        var36 = -0.0059245294;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 54.0) {
                            var36 = -0.0003499903;
                        } else {
                            var36 = -0.0067137866;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var36 = 0.0007229414;
                        } else {
                            var36 = 0.009412363;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var36 = -0.006040332;
                } else {
                    var36 = -0.0020783169;
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 4.61) {
                        if (input[4] < 4.0) {
                            var36 = 0.004749631;
                        } else {
                            var36 = -0.0013841101;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var36 = 0.005712641;
                        } else {
                            var36 = 0.017477762;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var36 = 0.0053596827;
                        } else {
                            var36 = 0.01115743;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var36 = 0.005015887;
                        } else {
                            var36 = 0.024201488;
                        }
                    }
                }
            }
        }
        double var37;
        if (input[3] < 3.29) {
            if (input[3] < 2.42) {
                if (input[1] < 5.0) {
                    if (input[3] < 1.53) {
                        if (input[3] < 1.21) {
                            var37 = -0.010766129;
                        } else {
                            var37 = -0.008988286;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var37 = -0.010184665;
                        } else {
                            var37 = -0.0053817756;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 57.0) {
                            var37 = -0.007004518;
                        } else {
                            var37 = -0.010017545;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var37 = -0.009614636;
                        } else {
                            var37 = 0.0015413807;
                        }
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var37 = -0.0075501273;
                        } else {
                            var37 = -0.0056351894;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var37 = -0.003995023;
                        } else {
                            var37 = -0.0019324732;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 53.0) {
                            var37 = -0.00037976867;
                        } else {
                            var37 = -0.007135605;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var37 = -0.00010165417;
                        } else {
                            var37 = 0.007891815;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var37 = -0.005740276;
                } else {
                    var37 = -0.0019750397;
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[3] < 4.33) {
                        if (input[1] < 2.0) {
                            var37 = -0.00042066962;
                        } else {
                            var37 = 0.005555048;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var37 = 0.0056343595;
                        } else {
                            var37 = 0.0140660135;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var37 = 0.005024714;
                        } else {
                            var37 = 0.010692568;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var37 = 0.005050685;
                        } else {
                            var37 = 0.026364625;
                        }
                    }
                }
            }
        }
        double var38;
        if (input[3] < 3.09) {
            if (input[3] < 2.08) {
                if (input[2] < 4.0) {
                    if (input[0] < 10.0) {
                        if (input[3] < 1.77) {
                            var38 = -0.008512614;
                        } else {
                            var38 = -0.004668552;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var38 = -0.00894406;
                        } else {
                            var38 = -0.010436962;
                        }
                    }
                } else {
                    if (input[3] < 1.33) {
                        if (input[1] < 5.0) {
                            var38 = -0.010501481;
                        } else {
                            var38 = -0.0069313482;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var38 = -0.012212173;
                        } else {
                            var38 = -0.0031290469;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 40.0) {
                        if (input[4] < 1.0) {
                            var38 = -0.0078051025;
                        } else {
                            var38 = -0.0014596436;
                        }
                    } else {
                        if (input[0] < 85.0) {
                            var38 = -0.005991164;
                        } else {
                            var38 = -0.010593823;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var38 = -0.0060064252;
                        } else {
                            var38 = -0.0009170319;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var38 = -0.0052401074;
                        } else {
                            var38 = 0.007097145;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var38 = -0.005457576;
                } else {
                    var38 = -0.0019071402;
                }
            } else {
                if (input[3] < 4.25) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var38 = -0.00090646476;
                        } else {
                            var38 = 0.004819377;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var38 = 0.0015450448;
                        } else {
                            var38 = 0.013452056;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var38 = 0.0054398985;
                        } else {
                            var38 = 0.011296234;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var38 = 0.0102428645;
                        } else {
                            var38 = 0.02878259;
                        }
                    }
                }
            }
        }
        double var39;
        if (input[3] < 3.29) {
            if (input[3] < 2.16) {
                if (input[2] < 5.0) {
                    if (input[0] < 50.0) {
                        if (input[3] < 1.79) {
                            var39 = -0.008401122;
                        } else {
                            var39 = -0.005698143;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var39 = -0.010007357;
                        } else {
                            var39 = -0.008131981;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var39 = -0.010091598;
                        } else {
                            var39 = -0.006855627;
                        }
                    } else {
                        if (input[3] < 1.28) {
                            var39 = -0.009516469;
                        } else {
                            var39 = -0.001586802;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[3] < 2.56) {
                        if (input[0] < 74.0) {
                            var39 = -0.0052789208;
                        } else {
                            var39 = -0.007400825;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var39 = -0.0039407513;
                        } else {
                            var39 = -0.0011780569;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 67.0) {
                            var39 = -0.0017523086;
                        } else {
                            var39 = -0.007883864;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var39 = -0.0044107363;
                        } else {
                            var39 = 0.00504068;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var39 = -0.0051840213;
                } else {
                    var39 = -0.0017821221;
                }
            } else {
                if (input[3] < 4.58) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var39 = 0.0044175694;
                        } else {
                            var39 = -0.0015813754;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var39 = 0.003168491;
                        } else {
                            var39 = 0.01132708;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var39 = 0.0051414967;
                        } else {
                            var39 = 0.013013755;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var39 = 0.017701764;
                        } else {
                            var39 = 0.03578317;
                        }
                    }
                }
            }
        }
        double var40;
        if (input[3] < 2.53) {
            if (input[3] < 1.77) {
                if (input[0] < 40.0) {
                    if (input[3] < 1.53) {
                        if (input[2] < 10.0) {
                            var40 = -0.007492897;
                        } else {
                            var40 = -0.003144091;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var40 = -0.00744816;
                        } else {
                            var40 = -0.00035409196;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var40 = -0.007902112;
                        } else {
                            var40 = -0.00927897;
                        }
                    } else {
                        if (input[3] < 1.48) {
                            var40 = -0.0073005045;
                        } else {
                            var40 = -0.0010578263;
                        }
                    }
                }
            } else {
                if (input[2] < 6.0) {
                    if (input[0] < 82.0) {
                        if (input[1] < 2.0) {
                            var40 = -0.0077694566;
                        } else {
                            var40 = -0.00459025;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var40 = -0.011121734;
                        } else {
                            var40 = -0.0040490935;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var40 = -0.008223487;
                        } else {
                            var40 = -0.0020285747;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var40 = -0.008475214;
                        } else {
                            var40 = 0.0067350813;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        var40 = -0.0068136477;
                    } else {
                        var40 = -0.004953528;
                    }
                } else {
                    if (input[3] < 2.59) {
                        if (input[1] < 2.0) {
                            var40 = -0.004996644;
                        } else {
                            var40 = -0.00043478012;
                        }
                    } else {
                        if (input[3] < 2.64) {
                            var40 = -0.000679878;
                        } else {
                            var40 = -0.0017236992;
                        }
                    }
                }
            } else {
                if (input[3] < 3.83) {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var40 = 0.003010529;
                        } else {
                            var40 = -0.0019002432;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var40 = 0.00012959786;
                        } else {
                            var40 = 0.012994147;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var40 = 0.0048564537;
                        } else {
                            var40 = 0.008767614;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var40 = 0.009023596;
                        } else {
                            var40 = 0.024028903;
                        }
                    }
                }
            }
        }
        double var41;
        if (input[3] < 3.31) {
            if (input[3] < 2.18) {
                if (input[1] < 5.0) {
                    if (input[0] < 56.0) {
                        if (input[1] < 2.0) {
                            var41 = -0.0089123845;
                        } else {
                            var41 = -0.00462983;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var41 = -0.008754797;
                        } else {
                            var41 = -0.006841717;
                        }
                    }
                } else {
                    if (input[2] < 8.0) {
                        if (input[2] < 4.0) {
                            var41 = -0.007094774;
                        } else {
                            var41 = -0.003093116;
                        }
                    } else {
                        if (input[3] < 1.45) {
                            var41 = -0.003776367;
                        } else {
                            var41 = 0.0044571175;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[3] < 2.56) {
                        if (input[1] < 2.0) {
                            var41 = -0.005686542;
                        } else {
                            var41 = -0.0044109025;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var41 = -0.0037419994;
                        } else {
                            var41 = -0.0012011029;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 71.0) {
                            var41 = -0.0016534537;
                        } else {
                            var41 = -0.0076067173;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var41 = -0.0037831727;
                        } else {
                            var41 = 0.004695487;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var41 = -0.0046800324;
                } else {
                    var41 = -0.0016077854;
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 4.61) {
                        if (input[4] < 4.0) {
                            var41 = 0.0040517775;
                        } else {
                            var41 = -0.0017813601;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var41 = 0.004443883;
                        } else {
                            var41 = 0.014068848;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var41 = 0.003967158;
                        } else {
                            var41 = 0.008857891;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var41 = 0.0035341352;
                        } else {
                            var41 = 0.01988454;
                        }
                    }
                }
            }
        }
        double var42;
        if (input[3] < 3.31) {
            if (input[3] < 2.08) {
                if (input[2] < 4.0) {
                    if (input[0] < 10.0) {
                        if (input[3] < 1.57) {
                            var42 = -0.007585201;
                        } else {
                            var42 = -0.0040576993;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var42 = -0.0072907056;
                        } else {
                            var42 = -0.008738862;
                        }
                    }
                } else {
                    if (input[3] < 1.35) {
                        if (input[3] < 1.11) {
                            var42 = -0.009978374;
                        } else {
                            var42 = -0.006899149;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var42 = -0.010300562;
                        } else {
                            var42 = -0.0021831405;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 2.52) {
                        if (input[0] < 62.0) {
                            var42 = -0.0037209743;
                        } else {
                            var42 = -0.006678217;
                        }
                    } else {
                        if (input[0] < 46.0) {
                            var42 = 0.00005907413;
                        } else {
                            var42 = -0.0029621732;
                        }
                    }
                } else {
                    if (input[2] < 9.0) {
                        if (input[4] < 3.0) {
                            var42 = -0.0034161457;
                        } else {
                            var42 = 0.0013958161;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var42 = -0.0007338909;
                        } else {
                            var42 = 0.015483982;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var42 = -0.00444757;
                } else {
                    var42 = -0.00152789;
                }
            } else {
                if (input[3] < 4.58) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var42 = 0.003837748;
                        } else {
                            var42 = -0.0016931195;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var42 = 0.002536312;
                        } else {
                            var42 = 0.009904515;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var42 = 0.0044464055;
                        } else {
                            var42 = 0.011443275;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var42 = 0.015683187;
                        } else {
                            var42 = 0.032309756;
                        }
                    }
                }
            }
        }
        double var43;
        if (input[3] < 3.09) {
            if (input[3] < 2.08) {
                if (input[2] < 4.0) {
                    if (input[0] < 10.0) {
                        if (input[1] < 2.0) {
                            var43 = -0.0083350865;
                        } else {
                            var43 = -0.0043504504;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var43 = -0.0069297785;
                        } else {
                            var43 = -0.008304719;
                        }
                    }
                } else {
                    if (input[3] < 1.42) {
                        if (input[3] < 1.21) {
                            var43 = -0.008717722;
                        } else {
                            var43 = -0.005777666;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var43 = -0.009996632;
                        } else {
                            var43 = -0.0018051807;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 37.0) {
                        if (input[4] < 1.0) {
                            var43 = -0.006406662;
                        } else {
                            var43 = -0.0008796625;
                        }
                    } else {
                        if (input[0] < 85.0) {
                            var43 = -0.0048850602;
                        } else {
                            var43 = -0.00888076;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var43 = -0.004830177;
                        } else {
                            var43 = -0.00065029477;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var43 = -0.004218241;
                        } else {
                            var43 = 0.005846249;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var43 = -0.004257118;
                } else {
                    var43 = -0.0014634534;
                }
            } else {
                if (input[3] < 4.25) {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var43 = -0.0009771723;
                        } else {
                            var43 = 0.0034286452;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var43 = 0.00088204484;
                        } else {
                            var43 = 0.010803256;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var43 = 0.0041849944;
                        } else {
                            var43 = 0.008971473;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var43 = 0.007952781;
                        } else {
                            var43 = 0.023736006;
                        }
                    }
                }
            }
        }
        double var44;
        if (input[3] < 3.31) {
            if (input[3] < 2.42) {
                if (input[1] < 5.0) {
                    if (input[0] < 38.0) {
                        if (input[3] < 1.6) {
                            var44 = -0.0061117928;
                        } else {
                            var44 = -0.0022442054;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var44 = -0.0070262463;
                        } else {
                            var44 = -0.0046473737;
                        }
                    }
                } else {
                    if (input[2] < 8.0) {
                        if (input[2] < 4.0) {
                            var44 = -0.0059022573;
                        } else {
                            var44 = -0.0016061198;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var44 = -0.0041362154;
                        } else {
                            var44 = 0.0047709327;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var44 = -0.0030271288;
                        } else {
                            var44 = -0.0066240514;
                        }
                    } else {
                        var44 = -0.0042178486;
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var44 = -0.0034070616;
                        } else {
                            var44 = -0.0007142007;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var44 = -0.0013833494;
                        } else {
                            var44 = 0.0036252213;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var44 = -0.0040152003;
                } else {
                    var44 = -0.0013792589;
                }
            } else {
                if (input[3] < 4.58) {
                    if (input[1] < 2.0) {
                        if (input[4] < 4.0) {
                            var44 = 0.003630472;
                        } else {
                            var44 = -0.001715764;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var44 = 0.0022342147;
                        } else {
                            var44 = 0.008978033;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var44 = 0.0040199696;
                        } else {
                            var44 = 0.0104349805;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var44 = 0.014374495;
                        } else {
                            var44 = 0.029607559;
                        }
                    }
                }
            }
        }
        double var45;
        if (input[3] < 3.31) {
            if (input[3] < 2.42) {
                if (input[1] < 5.0) {
                    if (input[0] < 57.0) {
                        if (input[1] < 2.0) {
                            var45 = -0.0068868804;
                        } else {
                            var45 = -0.0033713758;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var45 = -0.0071738595;
                        } else {
                            var45 = -0.0048485016;
                        }
                    }
                } else {
                    if (input[2] < 9.0) {
                        if (input[2] < 4.0) {
                            var45 = -0.0056116143;
                        } else {
                            var45 = -0.001153241;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var45 = -0.0024636928;
                        } else {
                            var45 = 0.008720522;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var45 = -0.0028895342;
                        } else {
                            var45 = -0.006302046;
                        }
                    } else {
                        var45 = -0.0040116417;
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var45 = -0.0032383297;
                        } else {
                            var45 = -0.0006787839;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var45 = -0.0013147753;
                        } else {
                            var45 = 0.0034447617;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var45 = -0.0038157608;
                    } else {
                        var45 = -0.0013107246;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 52.0) {
                            var45 = 0.005787658;
                        } else {
                            var45 = 0.0030383787;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var45 = 0.000059806644;
                        } else {
                            var45 = 0.0035674467;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.65) {
                        if (input[1] < 2.0) {
                            var45 = -0.0014232186;
                        } else {
                            var45 = 0.005048204;
                        }
                    } else {
                        if (input[3] < 4.77) {
                            var45 = 0.009161217;
                        } else {
                            var45 = 0.01666131;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 45.0) {
                            var45 = 0.007033615;
                        } else {
                            var45 = -0.0034095314;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var45 = 0.010561031;
                        } else {
                            var45 = 0.022236409;
                        }
                    }
                }
            }
        }
        double var46;
        if (input[3] < 2.93) {
            if (input[3] < 1.85) {
                if (input[0] < 38.0) {
                    if (input[3] < 1.6) {
                        if (input[2] < 10.0) {
                            var46 = -0.0055990843;
                        } else {
                            var46 = -0.0009537038;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var46 = -0.0048747743;
                        } else {
                            var46 = 0.0017574624;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[1] < 4.0) {
                            var46 = -0.006934861;
                        } else {
                            var46 = -0.005795866;
                        }
                    } else {
                        if (input[2] < 7.0) {
                            var46 = -0.006252923;
                        } else {
                            var46 = -0.0014924108;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 66.0) {
                        if (input[0] < 18.0) {
                            var46 = -0.000840123;
                        } else {
                            var46 = -0.0040464113;
                        }
                    } else {
                        if (input[3] < 2.24) {
                            var46 = -0.009470846;
                        } else {
                            var46 = -0.0057695075;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[1] < 2.0) {
                            var46 = -0.005407817;
                        } else {
                            var46 = -0.00029776717;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var46 = -0.0052105496;
                        } else {
                            var46 = 0.007836307;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var46 = -0.0036704508;
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.13) {
                            var46 = -0.00015683075;
                        } else {
                            var46 = -0.0012335022;
                        }
                    } else {
                        if (input[3] < 3.24) {
                            var46 = -0.0018501645;
                        } else {
                            var46 = -0.001252334;
                        }
                    }
                }
            } else {
                if (input[3] < 4.2) {
                    if (input[1] < 4.0) {
                        if (input[0] < 59.0) {
                            var46 = 0.0029965823;
                        } else {
                            var46 = -0.0011385247;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var46 = 0.0004306257;
                        } else {
                            var46 = 0.008811611;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var46 = 0.0035241467;
                        } else {
                            var46 = 0.0076107937;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var46 = 0.0018748485;
                        } else {
                            var46 = 0.017837865;
                        }
                    }
                }
            }
        }
        double var47;
        if (input[3] < 3.31) {
            if (input[3] < 2.18) {
                if (input[2] < 4.0) {
                    if (input[0] < 50.0) {
                        if (input[3] < 1.74) {
                            var47 = -0.006386877;
                        } else {
                            var47 = -0.0039868946;
                        }
                    } else {
                        if (input[3] < 1.62) {
                            var47 = -0.005731606;
                        } else {
                            var47 = -0.00828812;
                        }
                    }
                } else {
                    if (input[3] < 1.42) {
                        if (input[3] < 1.21) {
                            var47 = -0.0075379685;
                        } else {
                            var47 = -0.0047649867;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var47 = -0.008242345;
                        } else {
                            var47 = -0.0010414602;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var47 = -0.0031991892;
                        } else {
                            var47 = -0.0061552045;
                        }
                    } else {
                        var47 = -0.003635641;
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[0] < 74.0) {
                            var47 = -0.0007927254;
                        } else {
                            var47 = -0.0039316663;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var47 = 0.0004454996;
                        } else {
                            var47 = 0.0098065855;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var47 = -0.0034439121;
                    } else {
                        var47 = -0.0011836863;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.42) {
                            var47 = -0.000009582042;
                        } else {
                            var47 = 0.004687927;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var47 = -0.00016958399;
                        } else {
                            var47 = 0.0032088447;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.65) {
                        if (input[1] < 2.0) {
                            var47 = -0.0014853835;
                        } else {
                            var47 = 0.0045636427;
                        }
                    } else {
                        if (input[3] < 4.77) {
                            var47 = 0.00833999;
                        } else {
                            var47 = 0.015342786;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.65) {
                            var47 = -0.0007420338;
                        } else {
                            var47 = 0.012155365;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var47 = 0.009637173;
                        } else {
                            var47 = 0.020534057;
                        }
                    }
                }
            }
        }
        double var48;
        if (input[3] < 2.52) {
            if (input[2] < 9.0) {
                if (input[1] < 5.0) {
                    if (input[0] < 59.0) {
                        if (input[3] < 1.6) {
                            var48 = -0.0055012656;
                        } else {
                            var48 = -0.0032292851;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var48 = -0.0050844476;
                        } else {
                            var48 = -0.0066572987;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 1.31) {
                            var48 = -0.0026104494;
                        } else {
                            var48 = -0.008658289;
                        }
                    } else {
                        if (input[3] < 1.51) {
                            var48 = -0.0049616774;
                        } else {
                            var48 = 0.0005938715;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 1.68) {
                        if (input[0] < 47.0) {
                            var48 = -0.005501077;
                        } else {
                            var48 = -0.00811315;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var48 = -0.0059854323;
                        } else {
                            var48 = -0.00081004197;
                        }
                    }
                } else {
                    if (input[4] < 3.0) {
                        if (input[4] < 1.0) {
                            var48 = -0.0063824463;
                        } else {
                            var48 = 0.00077546143;
                        }
                    } else {
                        if (input[3] < 1.49) {
                            var48 = 0.0044935797;
                        } else {
                            var48 = 0.0123210745;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var48 = -0.0030414115;
                        } else {
                            var48 = -0.0053062937;
                        }
                    } else {
                        var48 = -0.0033320317;
                    }
                } else {
                    if (input[3] < 2.53) {
                        var48 = 0.0015382052;
                    } else {
                        if (input[3] < 2.59) {
                            var48 = -0.0031938553;
                        } else {
                            var48 = -0.001133276;
                        }
                    }
                }
            } else {
                if (input[3] < 3.69) {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var48 = 0.0018575339;
                        } else {
                            var48 = -0.0022469356;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var48 = 0.00040600073;
                        } else {
                            var48 = 0.009463294;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var48 = 0.0030451254;
                        } else {
                            var48 = 0.0060258987;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var48 = 0.004867733;
                        } else {
                            var48 = 0.016694453;
                        }
                    }
                }
            }
        }
        double var49;
        if (input[3] < 3.36) {
            if (input[3] < 2.08) {
                if (input[1] < 3.0) {
                    if (input[0] < 6.0) {
                        if (input[1] < 2.0) {
                            var49 = -0.0067358613;
                        } else {
                            var49 = -0.001678961;
                        }
                    } else {
                        if (input[0] < 36.0) {
                            var49 = -0.0051113158;
                        } else {
                            var49 = -0.0061473264;
                        }
                    }
                } else {
                    if (input[2] < 9.0) {
                        if (input[3] < 1.18) {
                            var49 = -0.007152173;
                        } else {
                            var49 = -0.0035019126;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var49 = -0.0040080734;
                        } else {
                            var49 = 0.004204434;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 37.0) {
                        if (input[4] < 1.0) {
                            var49 = -0.004669843;
                        } else {
                            var49 = 0.00019153295;
                        }
                    } else {
                        if (input[0] < 85.0) {
                            var49 = -0.0034505401;
                        } else {
                            var49 = -0.00732158;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var49 = -0.0036291895;
                        } else {
                            var49 = -0.00016911227;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var49 = -0.00093070744;
                        } else {
                            var49 = 0.0071155317;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var49 = -0.003106928;
                    } else {
                        var49 = -0.0010682651;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.42) {
                            var49 = -0.0017652194;
                        } else {
                            var49 = 0.00432282;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var49 = -0.0004500302;
                        } else {
                            var49 = 0.0027429182;
                        }
                    }
                }
            } else {
                if (input[3] < 4.77) {
                    if (input[2] < 4.0) {
                        if (input[0] < 45.0) {
                            var49 = 0.0046696733;
                        } else {
                            var49 = -0.0026452597;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var49 = -0.0033748045;
                        } else {
                            var49 = 0.010545206;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 3.0) {
                            var49 = 0.014180131;
                        } else {
                            var49 = 0.006338352;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var49 = 0.012912949;
                        } else {
                            var49 = 0.026404051;
                        }
                    }
                }
            }
        }
        double var50;
        if (input[3] < 2.52) {
            if (input[2] < 9.0) {
                if (input[1] < 5.0) {
                    if (input[0] < 59.0) {
                        if (input[1] < 2.0) {
                            var50 = -0.005443213;
                        } else {
                            var50 = -0.003145421;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var50 = -0.004567336;
                        } else {
                            var50 = -0.0061215544;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 1.31) {
                            var50 = -0.002251777;
                        } else {
                            var50 = -0.008097044;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var50 = -0.0042175525;
                        } else {
                            var50 = 0.000860784;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 1.68) {
                        if (input[0] < 52.0) {
                            var50 = -0.005121441;
                        } else {
                            var50 = -0.007637933;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var50 = -0.005484224;
                        } else {
                            var50 = -0.0007068019;
                        }
                    }
                } else {
                    if (input[4] < 3.0) {
                        if (input[4] < 1.0) {
                            var50 = -0.005945646;
                        } else {
                            var50 = 0.00089391734;
                        }
                    } else {
                        if (input[3] < 1.49) {
                            var50 = 0.004092134;
                        } else {
                            var50 = 0.011481237;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 2.8) {
                        if (input[1] < 2.0) {
                            var50 = -0.0027664483;
                        } else {
                            var50 = -0.004961658;
                        }
                    } else {
                        var50 = -0.003018459;
                    }
                } else {
                    if (input[3] < 2.53) {
                        var50 = 0.0015039801;
                    } else {
                        if (input[3] < 2.59) {
                            var50 = -0.002949704;
                        } else {
                            var50 = -0.0010161888;
                        }
                    }
                }
            } else {
                if (input[3] < 4.19) {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var50 = 0.0025452327;
                        } else {
                            var50 = -0.0014764651;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var50 = 0.0002628494;
                        } else {
                            var50 = 0.009066475;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var50 = 0.0028416077;
                        } else {
                            var50 = 0.0062378175;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var50 = 0.0052249855;
                        } else {
                            var50 = 0.016986193;
                        }
                    }
                }
            }
        }
        double var51;
        if (input[3] < 3.36) {
            if (input[3] < 2.22) {
                if (input[2] < 4.0) {
                    if (input[0] < 9.0) {
                        if (input[3] < 1.85) {
                            var51 = -0.0045238324;
                        } else {
                            var51 = -0.00084961654;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var51 = -0.004602892;
                        } else {
                            var51 = -0.0057891845;
                        }
                    }
                } else {
                    if (input[3] < 1.35) {
                        if (input[3] < 1.11) {
                            var51 = -0.007154278;
                        } else {
                            var51 = -0.004627153;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var51 = -0.006808303;
                        } else {
                            var51 = -0.0007384832;
                        }
                    }
                }
            } else {
                if (input[2] < 7.0) {
                    if (input[0] < 41.0) {
                        if (input[4] < 1.0) {
                            var51 = -0.0043673282;
                        } else {
                            var51 = 0.0005746034;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var51 = -0.0038784393;
                        } else {
                            var51 = -0.001317433;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var51 = -0.0031320339;
                        } else {
                            var51 = 0.00017441569;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var51 = -0.000054255997;
                        } else {
                            var51 = 0.008609127;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var51 = -0.0028027107;
                    } else {
                        var51 = -0.0009647172;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.42) {
                            var51 = -0.0016781529;
                        } else {
                            var51 = 0.0040062396;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var51 = -0.00032547454;
                        } else {
                            var51 = 0.002618816;
                        }
                    }
                }
            } else {
                if (input[3] < 4.63) {
                    if (input[1] < 2.0) {
                        if (input[2] < 4.0) {
                            var51 = 0.0013061843;
                        } else {
                            var51 = -0.0041672173;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var51 = -0.00034147268;
                        } else {
                            var51 = 0.009524359;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 3.0) {
                            var51 = 0.011568961;
                        } else {
                            var51 = 0.0051982165;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var51 = 0.0037754774;
                        } else {
                            var51 = 0.020002192;
                        }
                    }
                }
            }
        }
        double var52;
        if (input[3] < 3.18) {
            if (input[3] < 2.08) {
                if (input[0] < 40.0) {
                    if (input[3] < 1.6) {
                        if (input[2] < 10.0) {
                            var52 = -0.0041748695;
                        } else {
                            var52 = -0.0005751813;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var52 = -0.006791328;
                        } else {
                            var52 = 0.0005968184;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[0] < 78.0) {
                            var52 = -0.0045896913;
                        } else {
                            var52 = -0.0056469655;
                        }
                    } else {
                        if (input[3] < 1.48) {
                            var52 = -0.00461178;
                        } else {
                            var52 = -0.00090992183;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 37.0) {
                        if (input[1] < 2.0) {
                            var52 = -0.0031891246;
                        } else {
                            var52 = 0.000066756525;
                        }
                    } else {
                        if (input[0] < 85.0) {
                            var52 = -0.0033427868;
                        } else {
                            var52 = -0.0063259304;
                        }
                    }
                } else {
                    if (input[1] < 3.0) {
                        if (input[1] < 2.0) {
                            var52 = -0.003248365;
                        } else {
                            var52 = -0.0008477413;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var52 = -0.0025637026;
                        } else {
                            var52 = 0.003165961;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var52 = -0.002681199;
                    } else {
                        if (input[3] < 3.31) {
                            var52 = -0.001232818;
                        } else {
                            var52 = -0.00091824395;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.42) {
                            var52 = 0.00013899992;
                        } else {
                            var52 = 0.0038076441;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var52 = 0.002398423;
                        } else {
                            var52 = -0.00060931116;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.49) {
                        if (input[1] < 2.0) {
                            var52 = -0.00220204;
                        } else {
                            var52 = 0.0025590304;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var52 = 0.00718641;
                        } else {
                            var52 = 0.014883895;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 45.0) {
                            var52 = 0.0049883267;
                        } else {
                            var52 = -0.0041234293;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var52 = 0.0075991573;
                        } else {
                            var52 = 0.016811498;
                        }
                    }
                }
            }
        }
        double var53;
        if (input[3] < 3.36) {
            if (input[3] < 2.42) {
                if (input[2] < 9.0) {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var53 = -0.0034096085;
                        } else {
                            var53 = -0.0051029786;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var53 = -0.005967251;
                        } else {
                            var53 = -0.0010994843;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.68) {
                            var53 = -0.0056941775;
                        } else {
                            var53 = -0.0018944032;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var53 = -0.0018471221;
                        } else {
                            var53 = 0.0089156395;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.55) {
                            var53 = -0.00055850233;
                        } else {
                            var53 = -0.0026606033;
                        }
                    } else {
                        if (input[3] < 2.8) {
                            var53 = -0.0048259753;
                        } else {
                            var53 = -0.0027941715;
                        }
                    }
                } else {
                    if (input[2] < 7.0) {
                        if (input[0] < 41.0) {
                            var53 = 0.0010169658;
                        } else {
                            var53 = -0.0020648;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var53 = 0.0003025403;
                        } else {
                            var53 = 0.0046511986;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var53 = -0.002530352;
                    } else {
                        var53 = -0.00087118894;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.22) {
                            var53 = 0.0044975663;
                        } else {
                            var53 = 0.0022111048;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var53 = -0.0003680828;
                        } else {
                            var53 = 0.002426368;
                        }
                    }
                }
            } else {
                if (input[3] < 4.77) {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var53 = 0.005221338;
                        } else {
                            var53 = -0.00038953227;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var53 = 0.00035977815;
                        } else {
                            var53 = 0.013508889;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 3.0) {
                            var53 = 0.012116456;
                        } else {
                            var53 = 0.0051011513;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var53 = 0.0107899895;
                        } else {
                            var53 = 0.02283819;
                        }
                    }
                }
            }
        }
        double var54;
        if (input[3] < 3.36) {
            if (input[3] < 2.08) {
                if (input[2] < 4.0) {
                    if (input[0] < 5.0) {
                        if (input[2] < 2.0) {
                            var54 = -0.0041762115;
                        } else {
                            var54 = -0.00021778271;
                        }
                    } else {
                        if (input[3] < 1.34) {
                            var54 = -0.003988856;
                        } else {
                            var54 = -0.0051970333;
                        }
                    }
                } else {
                    if (input[3] < 1.31) {
                        if (input[1] < 2.0) {
                            var54 = -0.002615973;
                        } else {
                            var54 = -0.0057497253;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var54 = -0.006410809;
                        } else {
                            var54 = -0.0007851504;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 52.0) {
                        if (input[1] < 2.0) {
                            var54 = -0.0031474729;
                        } else {
                            var54 = 0.0007388041;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var54 = -0.004116399;
                        } else {
                            var54 = -0.001372854;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[4] < 2.0) {
                            var54 = -0.0025954666;
                        } else {
                            var54 = 0.0009071398;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var54 = -0.00071392785;
                        } else {
                            var54 = 0.008104966;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var54 = -0.0024046842;
                    } else {
                        var54 = -0.0008279127;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 3.42) {
                            var54 = -0.0018014153;
                        } else {
                            var54 = 0.0034545665;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var54 = -0.0005655367;
                        } else {
                            var54 = 0.0021756962;
                        }
                    }
                }
            } else {
                if (input[3] < 4.63) {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var54 = 0.0045988294;
                        } else {
                            var54 = -0.0010184468;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var54 = -0.00055437133;
                        } else {
                            var54 = 0.013361898;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 3.0) {
                            var54 = 0.010142538;
                        } else {
                            var54 = 0.0043128594;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var54 = 0.002874576;
                        } else {
                            var54 = 0.017844778;
                        }
                    }
                }
            }
        }
        double var55;
        if (input[3] < 3.09) {
            if (input[3] < 1.77) {
                if (input[0] < 30.0) {
                    if (input[2] < 4.0) {
                        if (input[3] < 1.67) {
                            var55 = -0.0045743207;
                        } else {
                            var55 = -0.0023436302;
                        }
                    } else {
                        if (input[1] < 3.0) {
                            var55 = -0.0036376007;
                        } else {
                            var55 = 0.0005336682;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[1] < 2.0) {
                            var55 = -0.003816363;
                        } else {
                            var55 = -0.0050381287;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var55 = -0.0041302796;
                        } else {
                            var55 = 0.0011954466;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 66.0) {
                        if (input[0] < 18.0) {
                            var55 = -0.000103889986;
                        } else {
                            var55 = -0.0026901502;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var55 = -0.0016315178;
                        } else {
                            var55 = -0.0059217103;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.22) {
                            var55 = -0.0088030165;
                        } else {
                            var55 = -0.0037712287;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var55 = -0.0038405824;
                        } else {
                            var55 = 0.0019014688;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 2.0) {
                if (input[4] < 1.0) {
                    var55 = -0.0023232806;
                } else {
                    if (input[2] < 9.0) {
                        if (input[3] < 3.13) {
                            var55 = -0.00014194846;
                        } else {
                            var55 = -0.0008042051;
                        }
                    } else {
                        if (input[3] < 3.31) {
                            var55 = -0.0020295666;
                        } else {
                            var55 = -0.00074926805;
                        }
                    }
                }
            } else {
                if (input[3] < 4.45) {
                    if (input[0] < 59.0) {
                        if (input[1] < 2.0) {
                            var55 = -0.0012247501;
                        } else {
                            var55 = 0.005238797;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var55 = -0.0029815293;
                        } else {
                            var55 = 0.001984988;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[4] < 4.0) {
                            var55 = 0.0022608943;
                        } else {
                            var55 = 0.005657789;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var55 = 0.0011221779;
                        } else {
                            var55 = 0.015505249;
                        }
                    }
                }
            }
        }
        double var56;
        if (input[3] < 3.36) {
            if (input[3] < 2.42) {
                if (input[2] < 9.0) {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var56 = -0.002971676;
                        } else {
                            var56 = -0.004496557;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var56 = -0.0052515464;
                        } else {
                            var56 = -0.00086371804;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.68) {
                            var56 = -0.0050354335;
                        } else {
                            var56 = -0.0016668746;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var56 = -0.0016148984;
                        } else {
                            var56 = 0.008399122;
                        }
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[1] < 2.0) {
                        if (input[3] < 2.55) {
                            var56 = -0.00026005082;
                        } else {
                            var56 = -0.0023039372;
                        }
                    } else {
                        if (input[3] < 2.74) {
                            var56 = -0.0044887927;
                        } else {
                            var56 = -0.0025467472;
                        }
                    }
                } else {
                    if (input[2] < 9.0) {
                        if (input[0] < 30.0) {
                            var56 = 0.0018178596;
                        } else {
                            var56 = -0.0011934171;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var56 = -0.00011009148;
                        } else {
                            var56 = 0.007623656;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var56 = -0.0021698729;
                    } else {
                        var56 = -0.00074715243;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var56 = 0.0042754565;
                        } else {
                            var56 = 0.0020329945;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var56 = -0.00049501;
                        } else {
                            var56 = 0.0020642786;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.49) {
                        if (input[1] < 2.0) {
                            var56 = -0.0022296754;
                        } else {
                            var56 = 0.0026714024;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var56 = 0.005843585;
                        } else {
                            var56 = 0.012618314;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 45.0) {
                            var56 = 0.004180174;
                        } else {
                            var56 = -0.004059778;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var56 = 0.0062213684;
                        } else {
                            var56 = 0.0154248895;
                        }
                    }
                }
            }
        }
        double var57;
        if (input[3] < 3.54) {
            if (input[3] < 2.46) {
                if (input[2] < 9.0) {
                    if (input[0] < 78.0) {
                        if (input[1] < 5.0) {
                            var57 = -0.0029958251;
                        } else {
                            var57 = -0.0008797731;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var57 = -0.002970398;
                        } else {
                            var57 = -0.004837143;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.68) {
                            var57 = -0.004788159;
                        } else {
                            var57 = -0.001546879;
                        }
                    } else {
                        if (input[4] < 2.0) {
                            var57 = -0.003340814;
                        } else {
                            var57 = 0.006973145;
                        }
                    }
                }
            } else {
                if (input[0] < 70.0) {
                    if (input[4] < 2.0) {
                        if (input[4] < 1.0) {
                            var57 = -0.0029135998;
                        } else {
                            var57 = -0.00077407376;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var57 = -0.0025262998;
                        } else {
                            var57 = 0.0026025062;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 2.0) {
                            var57 = -0.00067080784;
                        } else {
                            var57 = -0.005328827;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var57 = -0.0015901485;
                        } else {
                            var57 = 0.0003304855;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var57 = -0.0020600723;
                    } else {
                        var57 = -0.00070919;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var57 = 0.005128023;
                        } else {
                            var57 = 0.0019327564;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var57 = -0.00091532985;
                        } else {
                            var57 = 0.001839052;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 4.86) {
                        if (input[2] < 4.0) {
                            var57 = 0.0025232069;
                        } else {
                            var57 = -0.0028137178;
                        }
                    } else {
                        var57 = 0.014679851;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 60.0) {
                            var57 = 0.003967432;
                        } else {
                            var57 = -0.005063355;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var57 = 0.0054431125;
                        } else {
                            var57 = 0.012479919;
                        }
                    }
                }
            }
        }
        double var58;
        if (input[3] < 3.36) {
            if (input[3] < 2.08) {
                if (input[0] < 40.0) {
                    if (input[3] < 1.6) {
                        if (input[2] < 4.0) {
                            var58 = -0.004135096;
                        } else {
                            var58 = -0.002243242;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var58 = -0.005599532;
                        } else {
                            var58 = 0.0009971593;
                        }
                    }
                } else {
                    if (input[1] < 5.0) {
                        if (input[4] < 1.0) {
                            var58 = -0.0049632937;
                        } else {
                            var58 = -0.003647862;
                        }
                    } else {
                        if (input[3] < 1.48) {
                            var58 = -0.0038003947;
                        } else {
                            var58 = -0.00048559453;
                        }
                    }
                }
            } else {
                if (input[1] < 5.0) {
                    if (input[0] < 49.0) {
                        if (input[1] < 2.0) {
                            var58 = -0.0027724593;
                        } else {
                            var58 = 0.0007835249;
                        }
                    } else {
                        if (input[2] < 7.0) {
                            var58 = -0.0027905835;
                        } else {
                            var58 = -0.00046666866;
                        }
                    }
                } else {
                    if (input[4] < 4.0) {
                        if (input[3] < 2.78) {
                            var58 = 0.0006641901;
                        } else {
                            var58 = -0.002564513;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var58 = -0.0006645494;
                        } else {
                            var58 = 0.007409654;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var58 = -0.0019584817;
                    } else {
                        var58 = -0.0006746375;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.22) {
                            var58 = 0.0037143633;
                        } else {
                            var58 = 0.0016566539;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var58 = 0.0018438168;
                        } else {
                            var58 = -0.0006097422;
                        }
                    }
                }
            } else {
                if (input[3] < 4.77) {
                    if (input[1] < 5.0) {
                        if (input[0] < 59.0) {
                            var58 = 0.0040508225;
                        } else {
                            var58 = -0.0006316147;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var58 = -0.000002249767;
                        } else {
                            var58 = 0.011032944;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[2] < 5.0) {
                            var58 = 0.01082727;
                        } else {
                            var58 = 0.0040864325;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var58 = 0.008622282;
                        } else {
                            var58 = 0.019174239;
                        }
                    }
                }
            }
        }
        double var59;
        if (input[3] < 2.52) {
            if (input[2] < 4.0) {
                if (input[0] < 9.0) {
                    if (input[3] < 1.85) {
                        if (input[3] < 1.49) {
                            var59 = -0.0039318255;
                        } else {
                            var59 = -0.0021695185;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var59 = -0.0048555415;
                        } else {
                            var59 = 0.0007456273;
                        }
                    }
                } else {
                    if (input[1] < 2.0) {
                        if (input[0] < 54.0) {
                            var59 = -0.0032875852;
                        } else {
                            var59 = -0.0020192114;
                        }
                    } else {
                        if (input[0] < 77.0) {
                            var59 = -0.003571462;
                        } else {
                            var59 = -0.005218399;
                        }
                    }
                }
            } else {
                if (input[1] < 3.0) {
                    if (input[3] < 1.82) {
                        if (input[0] < 6.0) {
                            var59 = -0.0016333141;
                        } else {
                            var59 = -0.004079197;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var59 = -0.0034279618;
                        } else {
                            var59 = -0.0011739053;
                        }
                    }
                } else {
                    if (input[3] < 1.21) {
                        if (input[3] < 1.11) {
                            var59 = -0.00740691;
                        } else {
                            var59 = -0.004097762;
                        }
                    } else {
                        if (input[4] < 1.0) {
                            var59 = -0.006344209;
                        } else {
                            var59 = 0.0013616786;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.8) {
                            var59 = -0.003510906;
                        } else {
                            var59 = -0.00193931;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var59 = -0.00030083663;
                        } else {
                            var59 = -0.00084053865;
                        }
                    }
                } else {
                    if (input[3] < 3.59) {
                        if (input[0] < 92.0) {
                            var59 = 0.00032083344;
                        } else {
                            var59 = -0.0042819707;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var59 = 0.002750265;
                        } else {
                            var59 = 0.0010567186;
                        }
                    }
                }
            } else {
                if (input[1] < 4.0) {
                    if (input[3] < 4.31) {
                        if (input[1] < 2.0) {
                            var59 = -0.002471583;
                        } else {
                            var59 = 0.0008267378;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var59 = 0.0043811174;
                        } else {
                            var59 = 0.011117416;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 45.0) {
                            var59 = 0.002962809;
                        } else {
                            var59 = -0.0034737145;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var59 = 0.0063420385;
                        } else {
                            var59 = 0.012903782;
                        }
                    }
                }
            }
        }
        double var60;
        if (input[3] < 3.54) {
            if (input[3] < 2.42) {
                if (input[2] < 9.0) {
                    if (input[0] < 76.0) {
                        if (input[1] < 5.0) {
                            var60 = -0.002626662;
                        } else {
                            var60 = -0.0005911232;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var60 = -0.002570387;
                        } else {
                            var60 = -0.004281906;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.68) {
                            var60 = -0.0042179874;
                        } else {
                            var60 = -0.0013907448;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var60 = -0.0013233246;
                        } else {
                            var60 = 0.00760023;
                        }
                    }
                }
            } else {
                if (input[0] < 73.0) {
                    if (input[4] < 2.0) {
                        if (input[4] < 1.0) {
                            var60 = -0.0025711737;
                        } else {
                            var60 = -0.0006273295;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var60 = -0.0022006023;
                        } else {
                            var60 = 0.0021511752;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 2.0) {
                            var60 = -0.0004644936;
                        } else {
                            var60 = -0.005272539;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var60 = -0.0014755692;
                        } else {
                            var60 = 0.00062814745;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var60 = -0.0017644715;
                    } else {
                        var60 = -0.00060849555;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var60 = 0.004573246;
                        } else {
                            var60 = 0.0016044521;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var60 = -0.00095974497;
                        } else {
                            var60 = 0.0016512625;
                        }
                    }
                }
            } else {
                if (input[1] < 2.0) {
                    if (input[3] < 4.53) {
                        if (input[2] < 4.0) {
                            var60 = 0.0012154828;
                        } else {
                            var60 = -0.0040478883;
                        }
                    } else {
                        if (input[3] < 4.86) {
                            var60 = 0.0033273553;
                        } else {
                            var60 = 0.013133434;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[0] < 60.0) {
                            var60 = 0.0034266394;
                        } else {
                            var60 = -0.004807969;
                        }
                    } else {
                        if (input[4] < 6.0) {
                            var60 = 0.0046347505;
                        } else {
                            var60 = 0.011219364;
                        }
                    }
                }
            }
        }
        double var61;
        if (input[3] < 3.54) {
            if (input[3] < 2.18) {
                if (input[2] < 4.0) {
                    if (input[3] < 1.31) {
                        if (input[0] < 60.0) {
                            var61 = -0.0031143397;
                        } else {
                            var61 = -0.0020117352;
                        }
                    } else {
                        if (input[0] < 50.0) {
                            var61 = -0.002915318;
                        } else {
                            var61 = -0.0044175102;
                        }
                    }
                } else {
                    if (input[3] < 1.42) {
                        if (input[3] < 1.18) {
                            var61 = -0.004724782;
                        } else {
                            var61 = -0.0025810427;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var61 = -0.0050181863;
                        } else {
                            var61 = 0.00014757171;
                        }
                    }
                }
            } else {
                if (input[2] < 9.0) {
                    if (input[0] < 30.0) {
                        if (input[1] < 2.0) {
                            var61 = -0.0024483937;
                        } else {
                            var61 = 0.0020291025;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var61 = -0.0023542752;
                        } else {
                            var61 = -0.0007569641;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[0] < 80.0) {
                            var61 = -0.000108413864;
                        } else {
                            var61 = -0.0026380143;
                        }
                    } else {
                        if (input[4] < 4.0) {
                            var61 = -0.00015486189;
                        } else {
                            var61 = 0.011584757;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var61 = -0.0016769294;
                    } else {
                        var61 = -0.00057830557;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var61 = 0.0043505966;
                        } else {
                            var61 = 0.0015253386;
                        }
                    } else {
                        if (input[3] < 4.08) {
                            var61 = -0.00065392256;
                        } else {
                            var61 = 0.001691062;
                        }
                    }
                }
            } else {
                if (input[3] < 4.77) {
                    if (input[2] < 4.0) {
                        if (input[0] < 36.0) {
                            var61 = 0.0035278648;
                        } else {
                            var61 = -0.002013673;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var61 = -0.0031832065;
                        } else {
                            var61 = 0.0068518817;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[2] < 5.0) {
                            var61 = 0.00966072;
                        } else {
                            var61 = 0.0032804408;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var61 = 0.00084778265;
                        } else {
                            var61 = 0.01455704;
                        }
                    }
                }
            }
        }
        double var62;
        if (input[3] < 3.36) {
            if (input[3] < 1.77) {
                if (input[0] < 30.0) {
                    if (input[3] < 1.53) {
                        if (input[2] < 4.0) {
                            var62 = -0.0034085498;
                        } else {
                            var62 = -0.0017133768;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var62 = -0.0022777265;
                        } else {
                            var62 = 0.002446225;
                        }
                    }
                } else {
                    if (input[0] < 90.0) {
                        if (input[1] < 4.0) {
                            var62 = -0.0033330151;
                        } else {
                            var62 = -0.0022908505;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var62 = -0.002495252;
                        } else {
                            var62 = -0.00491183;
                        }
                    }
                }
            } else {
                if (input[2] < 4.0) {
                    if (input[0] < 79.0) {
                        if (input[3] < 2.93) {
                            var62 = -0.0019319773;
                        } else {
                            var62 = 0.00021301348;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var62 = -0.00015754608;
                        } else {
                            var62 = -0.0054160836;
                        }
                    }
                } else {
                    if (input[4] < 1.0) {
                        if (input[3] < 2.22) {
                            var62 = -0.007671661;
                        } else {
                            var62 = -0.0027983831;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var62 = -0.0028823272;
                        } else {
                            var62 = 0.0016596662;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var62 = -0.00159817;
                    } else {
                        var62 = -0.0005485725;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.22) {
                            var62 = 0.0031704586;
                        } else {
                            var62 = 0.0012859873;
                        }
                    } else {
                        if (input[1] < 4.0) {
                            var62 = 0.0016213333;
                        } else {
                            var62 = -0.00071474223;
                        }
                    }
                }
            } else {
                if (input[3] < 4.63) {
                    if (input[1] < 5.0) {
                        if (input[0] < 29.0) {
                            var62 = 0.00454485;
                        } else {
                            var62 = -0.00019147826;
                        }
                    } else {
                        if (input[2] < 4.0) {
                            var62 = -0.0009234456;
                        } else {
                            var62 = 0.00987679;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[1] < 3.0) {
                            var62 = 0.007718183;
                        } else {
                            var62 = 0.0023337235;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var62 = 0.0016102694;
                        } else {
                            var62 = 0.013632839;
                        }
                    }
                }
            }
        }
        double var63;
        if (input[3] < 3.54) {
            if (input[3] < 2.42) {
                if (input[2] < 9.0) {
                    if (input[0] < 40.0) {
                        if (input[3] < 1.6) {
                            var63 = -0.0025911382;
                        } else {
                            var63 = -0.00047890734;
                        }
                    } else {
                        if (input[1] < 5.0) {
                            var63 = -0.003120751;
                        } else {
                            var63 = -0.0017003472;
                        }
                    }
                } else {
                    if (input[1] < 4.0) {
                        if (input[3] < 1.68) {
                            var63 = -0.0037244912;
                        } else {
                            var63 = -0.001255894;
                        }
                    } else {
                        if (input[4] < 3.0) {
                            var63 = -0.0011015439;
                        } else {
                            var63 = 0.0071056816;
                        }
                    }
                }
            } else {
                if (input[0] < 73.0) {
                    if (input[4] < 2.0) {
                        if (input[4] < 1.0) {
                            var63 = -0.002334385;
                        } else {
                            var63 = -0.00056992524;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var63 = -0.001953248;
                        } else {
                            var63 = 0.0019579006;
                        }
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[4] < 2.0) {
                            var63 = -0.00016600972;
                        } else {
                            var63 = -0.0047613503;
                        }
                    } else {
                        if (input[2] < 9.0) {
                            var63 = -0.001373911;
                        } else {
                            var63 = 0.0005904596;
                        }
                    }
                }
            }
        } else {
            if (input[4] < 4.0) {
                if (input[4] < 2.0) {
                    if (input[4] < 1.0) {
                        var63 = -0.0015144371;
                    } else {
                        var63 = -0.0005223855;
                    }
                } else {
                    if (input[2] < 4.0) {
                        if (input[3] < 4.04) {
                            var63 = 0.003984435;
                        } else {
                            var63 = 0.0013723243;
                        }
                    } else {
                        if (input[3] < 3.95) {
                            var63 = -0.0009148284;
                        } else {
                            var63 = 0.0014611791;
                        }
                    }
                }
            } else {
                if (input[3] < 4.77) {
                    if (input[2] < 4.0) {
                        if (input[0] < 36.0) {
                            var63 = 0.003208407;
                        } else {
                            var63 = -0.0019440994;
                        }
                    } else {
                        if (input[1] < 2.0) {
                            var63 = -0.0031139096;
                        } else {
                            var63 = 0.006300346;
                        }
                    }
                } else {
                    if (input[4] < 6.0) {
                        if (input[2] < 5.0) {
                            var63 = 0.008957397;
                        } else {
                            var63 = 0.0029065951;
                        }
                    } else {
                        if (input[2] < 5.0) {
                            var63 = 0.0068042316;
                        } else {
                            var63 = 0.016139928;
                        }
                    }
                }
            }
        }
        return var0 + var1 + var2 + var3 + var4 + var5 + var6 + var7 + var8 + var9 + var10 + var11 + var12 + var13 + var14 + var15 + var16 + var17 + var18 + var19 + var20 + var21 + var22 + var23 + var24 + var25 + var26 + var27 + var28 + var29 + var30 + var31 + var32 + var33 + var34 + var35 + var36 + var37 + var38 + var39 + var40 + var41 + var42 + var43 + var44 + var45 + var46 + var47 + var48 + var49 + var50 + var51 + var52 + var53 + var54 + var55 + var56 + var57 + var58 + var59 + var60 + var61 + var62 + var63;
    }
}
