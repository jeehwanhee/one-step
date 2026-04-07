package com.jeepark.onestep.util;

public class Model_A {

    public static double predict(double[] input) {
        double rawScore = score(input);

        return Math.max(0.0, Math.min(1.0, rawScore));
    }
    public static double score(double[] input) {
        double var0;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var0 = -0.036566667;
                    } else {
                        var0 = 0.023028573;
                    }
                } else {
                    var0 = 0.026788404;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var0 = 0.0030093768;
                    } else {
                        var0 = -0.06565408;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var0 = 0.026792247;
                    } else {
                        var0 = -0.005979109;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 3.0) {
                        var0 = 0.008050001;
                    } else {
                        var0 = -0.048176743;
                    }
                } else {
                    var0 = -0.07237632;
                }
            } else {
                var0 = 0.025523335;
            }
        }
        double var1;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var1 = -0.034738332;
                    } else {
                        var1 = 0.021054698;
                    }
                } else {
                    var1 = 0.024111217;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var1 = 0.002713141;
                    } else {
                        var1 = -0.05916161;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var1 = 0.024120444;
                    } else {
                        var1 = -0.0053851833;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 3.0) {
                        var1 = 0.0072737494;
                    } else {
                        var1 = -0.043471105;
                    }
                } else {
                    var1 = -0.06515027;
                }
            } else {
                var1 = 0.023098616;
            }
        }
        double var2;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var2 = -0.03300142;
                    } else {
                        var2 = 0.019250011;
                    }
                } else {
                    var2 = 0.021701582;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var2 = 0.002446066;
                    } else {
                        var2 = -0.053311188;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var2 = 0.02171508;
                    } else {
                        var2 = -0.0048502577;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 1.0) {
                    if (input[5] < 6.0) {
                        var2 = -0.042268552;
                    } else {
                        var2 = 0.0045568906;
                    }
                } else {
                    var2 = -0.058645666;
                }
            } else {
                var2 = 0.020904249;
            }
        }
        double var3;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var3 = -0.031351347;
                    } else {
                        var3 = 0.017600007;
                    }
                } else {
                    var3 = 0.019532762;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var3 = 0.0022052824;
                    } else {
                        var3 = -0.048039306;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var3 = 0.019549586;
                    } else {
                        var3 = -0.004368464;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[6] < 9.5) {
                    var3 = -0.052685466;
                } else {
                    if (input[5] < 6.0) {
                        var3 = -0.035852555;
                    } else {
                        var3 = 0.0057992213;
                    }
                }
            } else {
                var3 = 0.018918347;
            }
        }
        double var4;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var4 = -0.02978378;
                    } else {
                        var4 = 0.016091434;
                    }
                } else {
                    var4 = 0.017580694;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var4 = 0.001988197;
                    } else {
                        var4 = -0.04328875;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var4 = 0.01760004;
                    } else {
                        var4 = -0.003934528;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 3.0) {
                        var4 = 0.009527485;
                    } else {
                        var4 = -0.034038164;
                    }
                } else {
                    var4 = -0.047562547;
                }
            } else {
                var4 = 0.017121105;
            }
        }
        double var5;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var5 = -0.028294591;
                    } else {
                        var5 = 0.014712172;
                    }
                } else {
                    var5 = 0.01582371;
                }
            } else {
                if (input[5] < 24.0) {
                    if (input[2] < 1.0) {
                        var5 = 0.0050304434;
                    } else {
                        var5 = -0.05411023;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var5 = 0.020303838;
                    } else {
                        var5 = 0.005171289;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[6] < 9.5) {
                    if (input[5] < 24.0) {
                        var5 = -0.04313499;
                    } else {
                        var5 = -0.03517086;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var5 = 0.0018235332;
                    } else {
                        var5 = -0.035431292;
                    }
                }
            } else {
                var5 = 0.015494603;
            }
        }
        double var6;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var6 = -0.026879862;
                    } else {
                        var6 = 0.013451131;
                    }
                } else {
                    var6 = 0.014242314;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var6 = 0.0029606328;
                    } else {
                        var6 = -0.037796922;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var6 = -0.0005699213;
                    } else {
                        var6 = 0.016198356;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[6] < 9.5) {
                    if (input[5] < 24.0) {
                        var6 = -0.038828768;
                    } else {
                        var6 = -0.031746328;
                    }
                } else {
                    if (input[5] < 6.0) {
                        var6 = -0.028412092;
                    } else {
                        var6 = 0.0074959584;
                    }
                }
            } else {
                var6 = 0.014022618;
            }
        }
        double var7;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var7 = -0.02553587;
                    } else {
                        var7 = 0.012298173;
                    }
                } else {
                    var7 = 0.012818962;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var7 = 0.0026691963;
                    } else {
                        var7 = -0.034059227;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var7 = 0.013732444;
                    } else {
                        var7 = -0.004100424;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 3.0) {
                        var7 = 0.011247353;
                    } else {
                        var7 = -0.027093157;
                    }
                } else {
                    var7 = -0.034749098;
                }
            } else {
                var7 = 0.0126904715;
            }
        }
        double var8;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var8 = -0.024259076;
                    } else {
                        var8 = 0.011244046;
                    }
                } else {
                    var8 = 0.011537855;
                }
            } else {
                if (input[5] < 24.0) {
                    if (input[2] < 1.0) {
                        var8 = 0.0053590196;
                    } else {
                        var8 = -0.04661642;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var8 = 0.015894277;
                    } else {
                        var8 = 0.002291489;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 3.0) {
                        var8 = 0.010162788;
                    } else {
                        var8 = -0.024446849;
                    }
                } else {
                    var8 = -0.031279746;
                }
            } else {
                var8 = 0.011484875;
            }
        }
        double var9;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var9 = -0.023046121;
                    } else {
                        var9 = 0.010280269;
                    }
                } else {
                    var9 = 0.010384782;
                }
            } else {
                if (input[5] < 24.0) {
                    if (input[2] < 1.0) {
                        var9 = 0.0048251343;
                    } else {
                        var9 = -0.042004902;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var9 = 0.014311008;
                    } else {
                        var9 = 0.0020671128;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[6] < 9.5) {
                    if (input[5] < 24.0) {
                        var9 = -0.028412169;
                    } else {
                        var9 = -0.02245622;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var9 = 0.0049477513;
                    } else {
                        var9 = -0.027702367;
                    }
                }
            } else {
                var9 = 0.010393813;
            }
        }
        double var10;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var10 = -0.021893814;
                    } else {
                        var10 = 0.009399104;
                    }
                } else {
                    var10 = 0.009346942;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[7] < 2.0) {
                        var10 = 0.041962914;
                    } else {
                        var10 = -0.020381302;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var10 = 0.010450682;
                    } else {
                        var10 = -0.002632588;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[6] < 9.5) {
                    if (input[5] < 24.0) {
                        var10 = -0.025575746;
                    } else {
                        var10 = -0.02026969;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var10 = 0.00031335055;
                    } else {
                        var10 = -0.036476187;
                    }
                }
            } else {
                var10 = 0.009406402;
            }
        }
        double var11;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var11 = -0.020799125;
                    } else {
                        var11 = 0.008593465;
                    }
                } else {
                    var11 = 0.008412822;
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[5] < 24.0) {
                        var11 = 0.0011998678;
                    } else {
                        var11 = 0.012280545;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var11 = -0.027244015;
                    } else {
                        var11 = 0.0001635361;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[6] < 9.5) {
                    if (input[5] < 24.0) {
                        var11 = -0.023022484;
                    } else {
                        var11 = -0.018296063;
                    }
                } else {
                    if (input[5] < 6.0) {
                        var11 = -0.020337991;
                    } else {
                        var11 = 0.00933944;
                    }
                }
            } else {
                var11 = 0.008512795;
            }
        }
        double var12;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var12 = -0.019759169;
                    } else {
                        var12 = 0.007856886;
                    }
                } else {
                    var12 = 0.007572058;
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[5] < 24.0) {
                        var12 = 0.0010805771;
                    } else {
                        var12 = 0.011057253;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var12 = -0.024549883;
                    } else {
                        var12 = 0.00014729418;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 3.0) {
                        var12 = 0.011319501;
                    } else {
                        var12 = -0.018845668;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var12 = -0.01862503;
                    } else {
                        var12 = -0.021277389;
                    }
                }
            } else {
                var12 = 0.00770408;
            }
        }
        double var13;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var13 = -0.018771209;
                    } else {
                        var13 = 0.0071834363;
                    }
                } else {
                    var13 = 0.0068153194;
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[2] < 1.0) {
                        var13 = 0.009545351;
                    } else {
                        var13 = -0.0009668092;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var13 = 0.02814098;
                    } else {
                        var13 = -0.012221665;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[7] < 2.0) {
                    if (input[6] < 9.5) {
                        var13 = -0.01588255;
                    } else {
                        var13 = 0.006236407;
                    }
                } else {
                    if (input[6] < 11.5) {
                        var13 = -0.019246794;
                    } else {
                        var13 = 0.011301298;
                    }
                }
            } else {
                var13 = 0.0069721923;
            }
        }
        double var14;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var14 = -0.01783265;
                    } else {
                        var14 = 0.006567713;
                    }
                } else {
                    var14 = 0.0061342088;
                }
            } else {
                if (input[5] < 24.0) {
                    if (input[2] < 1.0) {
                        var14 = 0.0053950283;
                    } else {
                        var14 = -0.035028048;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var14 = 0.004780446;
                    } else {
                        var14 = 0.015081634;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[7] < 2.0) {
                    if (input[6] < 9.5) {
                        var14 = -0.014316355;
                    } else {
                        var14 = 0.005629176;
                    }
                } else {
                    if (input[6] < 11.5) {
                        var14 = -0.017325427;
                    } else {
                        var14 = 0.010359524;
                    }
                }
            } else {
                var14 = 0.0063098343;
            }
        }
        double var15;
        if (input[3] < 2.0) {
            if (input[7] < 2.0) {
                if (input[4] < 1.0) {
                    var15 = 0.005585129;
                } else {
                    if (input[5] < 12.0) {
                        var15 = 0.0393127;
                    } else {
                        var15 = 0.012262316;
                    }
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var15 = 0.0043639555;
                    } else {
                        var15 = -0.01595761;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var15 = 0.006567964;
                    } else {
                        var15 = -0.0013340517;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[7] < 2.0) {
                    if (input[5] < 12.0) {
                        var15 = -0.0127797825;
                    } else {
                        var15 = 0.0058237;
                    }
                } else {
                    if (input[6] < 11.5) {
                        var15 = -0.015595865;
                    } else {
                        var15 = 0.00949623;
                    }
                }
            } else {
                var15 = 0.005710402;
            }
        }
        double var16;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var16 = -0.016143136;
                    } else {
                        var16 = 0.005628291;
                    }
                } else {
                    if (input[6] < 9.4) {
                        var16 = 0.0047819647;
                    } else {
                        var16 = 0.005122974;
                    }
                }
            } else {
                if (input[5] < 24.0) {
                    if (input[2] < 1.0) {
                        var16 = 0.004573533;
                    } else {
                        var16 = -0.03131823;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var16 = 0.0035925477;
                    } else {
                        var16 = 0.013600387;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[7] < 2.0) {
                    if (input[6] < 9.5) {
                        var16 = -0.0121611385;
                    } else {
                        var16 = 0.0055910717;
                    }
                } else {
                    if (input[6] < 11.5) {
                        var16 = -0.014038962;
                    } else {
                        var16 = 0.008704878;
                    }
                }
            } else {
                var16 = 0.005167912;
            }
        }
        double var17;
        if (input[3] < 2.0) {
            if (input[7] < 2.0) {
                if (input[4] < 1.0) {
                    var17 = 0.0045225117;
                } else {
                    if (input[5] < 12.0) {
                        var17 = 0.036089938;
                    } else {
                        var17 = 0.010834678;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[2] < 1.0) {
                        var17 = 0.0059340266;
                    } else {
                        var17 = 0.000019193521;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var17 = -0.01411137;
                    } else {
                        var17 = 0.0016008381;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[7] < 2.0) {
                    if (input[5] < 12.0) {
                        var17 = -0.010847163;
                    } else {
                        var17 = 0.005700165;
                    }
                } else {
                    if (input[6] < 11.5) {
                        var17 = -0.012637483;
                    } else {
                        var17 = 0.00797947;
                    }
                }
            } else {
                var17 = 0.0046769613;
            }
        }
        double var18;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var18 = -0.01463041;
                    } else {
                        var18 = 0.004759633;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var18 = 0.004067044;
                    } else {
                        var18 = 0.0044172197;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[5] < 24.0) {
                        var18 = 0.000020454092;
                    } else {
                        var18 = 0.006909955;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var18 = 0.021502485;
                    } else {
                        var18 = -0.009632533;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[7] < 2.0) {
                    if (input[3] < 3.0) {
                        var18 = 0.0038406036;
                    } else {
                        var18 = -0.011408871;
                    }
                } else {
                    if (input[6] < 11.5) {
                        var18 = -0.01137591;
                    } else {
                        var18 = 0.0073145158;
                    }
                }
            } else {
                var18 = 0.004232652;
            }
        }
        double var19;
        if (input[3] < 2.0) {
            if (input[4] < 1.0) {
                if (input[6] < 6.3) {
                    if (input[5] < 6.0) {
                        var19 = -0.0138988895;
                    } else {
                        var19 = 0.004351666;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var19 = 0.0036606975;
                    } else {
                        var19 = 0.003976392;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var19 = -0.0037524567;
                    } else {
                        var19 = 0.0054963175;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var19 = -0.02760621;
                    } else {
                        var19 = 0.011663164;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[6] < 13.6) {
                    if (input[2] < 1.0) {
                        var19 = -0.006218196;
                    } else {
                        var19 = -0.010514051;
                    }
                } else {
                    var19 = 0.030105209;
                }
            } else {
                var19 = 0.003830551;
            }
        }
        double var20;
        if (input[3] < 2.0) {
            if (input[7] < 2.0) {
                if (input[4] < 1.0) {
                    var20 = 0.0032982025;
                } else {
                    if (input[5] < 12.0) {
                        var20 = 0.032662045;
                    } else {
                        var20 = 0.008491998;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var20 = -0.0019592058;
                    } else {
                        var20 = 0.004287988;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var20 = -0.013870838;
                    } else {
                        var20 = 0.007941917;
                    }
                }
            }
        } else {
            if (input[5] < 12.0) {
                if (input[6] < 13.6) {
                    if (input[6] < 12.5) {
                        var20 = -0.009261076;
                    } else {
                        var20 = -0.03362321;
                    }
                } else {
                    var20 = 0.028599948;
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[3] < 4.0) {
                        var20 = 0.02127586;
                    } else {
                        var20 = -0.010419718;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var20 = -0.0012720631;
                    } else {
                        var20 = -0.008962931;
                    }
                }
            }
        }
        double var21;
        if (input[3] < 2.0) {
            if (input[6] < 7.4) {
                if (input[7] < 2.0) {
                    if (input[5] < 24.0) {
                        var21 = 0.016379269;
                    } else {
                        var21 = 0.0021249566;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var21 = 0.00073372206;
                    } else {
                        var21 = -0.013020967;
                    }
                }
            } else {
                if (input[6] < 9.0) {
                    if (input[2] < 1.0) {
                        var21 = 0.0035487514;
                    } else {
                        var21 = -0.0020838713;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var21 = 0.0031371035;
                    } else {
                        var21 = 0.010462692;
                    }
                }
            }
        } else {
            if (input[5] < 24.0) {
                if (input[6] < 13.6) {
                    if (input[7] < 2.0) {
                        var21 = -0.0042717312;
                    } else {
                        var21 = -0.008497652;
                    }
                } else {
                    var21 = 0.027169948;
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[3] < 3.0) {
                        var21 = 0.044713613;
                    } else {
                        var21 = -0.00022639944;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var21 = 0.017986754;
                    } else {
                        var21 = -0.0065661543;
                    }
                }
            }
        }
        double var22;
        if (input[3] < 2.0) {
            if (input[6] < 7.4) {
                if (input[3] < 1.0) {
                    if (input[2] < 1.0) {
                        var22 = 0.0042985952;
                    } else {
                        var22 = -0.0019151645;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var22 = 0.013166132;
                    } else {
                        var22 = -0.010912626;
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[7] < 2.0) {
                        var22 = 0.002669905;
                    } else {
                        var22 = 0.0032062766;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var22 = -0.0007280762;
                    } else {
                        var22 = 0.008533023;
                    }
                }
            }
        } else {
            if (input[5] < 12.0) {
                if (input[6] < 13.6) {
                    if (input[6] < 12.5) {
                        var22 = -0.0075503313;
                    } else {
                        var22 = -0.030465296;
                    }
                } else {
                    var22 = 0.025811452;
                }
            } else {
                if (input[6] < 9.6) {
                    if (input[5] < 60.0) {
                        var22 = -0.0063642734;
                    } else {
                        var22 = 0.004351554;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var22 = 0.020235404;
                    } else {
                        var22 = -0.004087429;
                    }
                }
            }
        }
        double var23;
        if (input[3] < 2.0) {
            if (input[6] < 7.4) {
                if (input[3] < 1.0) {
                    if (input[2] < 1.0) {
                        var23 = 0.003871223;
                    } else {
                        var23 = -0.0017263463;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var23 = 0.011909365;
                    } else {
                        var23 = -0.009831022;
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[7] < 2.0) {
                        var23 = 0.0024031512;
                    } else {
                        var23 = 0.002886307;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var23 = 0.0036017187;
                    } else {
                        var23 = -0.00437325;
                    }
                }
            }
        } else {
            if (input[5] < 24.0) {
                if (input[6] < 13.6) {
                    if (input[3] < 3.0) {
                        var23 = -0.004689637;
                    } else {
                        var23 = -0.007617045;
                    }
                } else {
                    var23 = 0.02452088;
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[4] < 2.0) {
                        var23 = 0.042693015;
                    } else {
                        var23 = -0.0012408955;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var23 = 0.016433885;
                    } else {
                        var23 = -0.005597018;
                    }
                }
            }
        }
        double var24;
        if (input[3] < 2.0) {
            if (input[6] < 7.3) {
                if (input[5] < 12.0) {
                    if (input[7] < 2.0) {
                        var24 = 0.022602595;
                    } else {
                        var24 = -0.012399361;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var24 = 0.0036211596;
                    } else {
                        var24 = -0.003377253;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[4] < 3.0) {
                        var24 = 0.002266705;
                    } else {
                        var24 = 0.0054531908;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var24 = 0.0024513565;
                    } else {
                        var24 = -0.0040772166;
                    }
                }
            }
        } else {
            if (input[2] < 1.0) {
                if (input[6] < 10.4) {
                    if (input[5] < 24.0) {
                        var24 = -0.00544092;
                    } else {
                        var24 = 0.019032005;
                    }
                } else {
                    if (input[0] < 33.0) {
                        var24 = 0.02748709;
                    } else {
                        var24 = -0.010810267;
                    }
                }
            } else {
                if (input[5] < 60.0) {
                    if (input[6] < 12.9) {
                        var24 = -0.006314034;
                    } else {
                        var24 = -0.02725782;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var24 = -0.000762111;
                    } else {
                        var24 = 0.004946089;
                    }
                }
            }
        }
        double var25;
        if (input[3] < 2.0) {
            if (input[3] < 1.0) {
                if (input[6] < 5.3) {
                    if (input[4] < 3.0) {
                        var25 = 0.014962456;
                    } else {
                        var25 = 0.0016152522;
                    }
                } else {
                    if (input[6] < 5.4) {
                        var25 = -0.01305362;
                    } else {
                        var25 = 0.0021172995;
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[7] < 2.0) {
                        var25 = 0.0018823091;
                    } else {
                        var25 = 0.0029021816;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var25 = -0.013800168;
                    } else {
                        var25 = 0.000984943;
                    }
                }
            }
        } else {
            if (input[7] < 2.0) {
                if (input[6] < 9.5) {
                    if (input[5] < 12.0) {
                        var25 = -0.008888384;
                    } else {
                        var25 = 0.0016585788;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var25 = 0.034855064;
                    } else {
                        var25 = -0.0012221507;
                    }
                }
            } else {
                if (input[6] < 10.0) {
                    if (input[5] < 60.0) {
                        var25 = -0.0052476744;
                    } else {
                        var25 = 0.003260865;
                    }
                } else {
                    if (input[6] < 11.5) {
                        var25 = -0.023283014;
                    } else {
                        var25 = 0.005249148;
                    }
                }
            }
        }
        double var26;
        if (input[3] < 2.0) {
            if (input[7] < 2.0) {
                if (input[4] < 1.0) {
                    if (input[6] < 7.3) {
                        var26 = 0.00045531505;
                    } else {
                        var26 = 0.0017262578;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var26 = 0.004415726;
                    } else {
                        var26 = 0.024367759;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var26 = -0.0013729212;
                    } else {
                        var26 = 0.00274091;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var26 = -0.011910274;
                    } else {
                        var26 = 0.0070813294;
                    }
                }
            }
        } else {
            if (input[3] < 3.0) {
                if (input[4] < 1.0) {
                    if (input[2] < 2.0) {
                        var26 = 0.016572785;
                    } else {
                        var26 = -0.019799758;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var26 = -0.004698371;
                    } else {
                        var26 = 0.0025652905;
                    }
                }
            } else {
                if (input[6] < 13.6) {
                    if (input[4] < 1.0) {
                        var26 = -0.0104353195;
                    } else {
                        var26 = -0.0049691154;
                    }
                } else {
                    var26 = 0.020177728;
                }
            }
        }
        double var27;
        if (input[3] < 2.0) {
            if (input[6] < 7.4) {
                if (input[7] < 2.0) {
                    if (input[2] < 1.0) {
                        var27 = 0.0022713945;
                    } else {
                        var27 = 0.017527482;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var27 = 0.0008535792;
                    } else {
                        var27 = -0.009962377;
                    }
                }
            } else {
                if (input[6] < 7.5) {
                    if (input[4] < 2.0) {
                        var27 = -0.0017038741;
                    } else {
                        var27 = 0.009841709;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var27 = 0.0017292298;
                    } else {
                        var27 = 0.00022958627;
                    }
                }
            }
        } else {
            if (input[2] < 1.0) {
                if (input[6] < 9.5) {
                    if (input[5] < 24.0) {
                        var27 = -0.004439519;
                    } else {
                        var27 = 0.01755271;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var27 = 0.031270828;
                    } else {
                        var27 = 0.00040468373;
                    }
                }
            } else {
                if (input[6] < 10.2) {
                    if (input[6] < 10.1) {
                        var27 = -0.0042961566;
                    } else {
                        var27 = 0.020587686;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var27 = -0.003798134;
                    } else {
                        var27 = -0.030953694;
                    }
                }
            }
        }
        double var28;
        if (input[3] < 2.0) {
            if (input[3] < 1.0) {
                if (input[6] < 5.3) {
                    if (input[4] < 3.0) {
                        var28 = 0.013176123;
                    } else {
                        var28 = 0.0012679378;
                    }
                } else {
                    if (input[6] < 5.4) {
                        var28 = -0.011574108;
                    } else {
                        var28 = 0.0016100854;
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[7] < 2.0) {
                        var28 = 0.001344878;
                    } else {
                        var28 = 0.0026045782;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var28 = -0.011963967;
                    } else {
                        var28 = 0.00070203096;
                    }
                }
            }
        } else {
            if (input[7] < 2.0) {
                if (input[3] < 4.0) {
                    if (input[6] < 9.5) {
                        var28 = -0.0026821524;
                    } else {
                        var28 = 0.014178586;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var28 = -0.0114326;
                    } else {
                        var28 = 0.021287946;
                    }
                }
            } else {
                if (input[6] < 10.0) {
                    if (input[5] < 60.0) {
                        var28 = -0.0038428388;
                    } else {
                        var28 = 0.0029573385;
                    }
                } else {
                    if (input[6] < 11.5) {
                        var28 = -0.020027516;
                    } else {
                        var28 = 0.004683729;
                    }
                }
            }
        }
        double var29;
        if (input[3] < 2.0) {
            if (input[5] < 60.0) {
                if (input[2] < 1.0) {
                    if (input[3] < 1.0) {
                        var29 = 0.002211563;
                    } else {
                        var29 = 0.0006976493;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var29 = 0.0019437226;
                    } else {
                        var29 = -0.0128469765;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[3] < 1.0) {
                        var29 = -0.00001559771;
                    } else {
                        var29 = 0.002632265;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var29 = 0.0012241902;
                    } else {
                        var29 = 0.015977582;
                    }
                }
            }
        } else {
            if (input[2] < 1.0) {
                if (input[6] < 9.5) {
                    if (input[5] < 24.0) {
                        var29 = -0.003592488;
                    } else {
                        var29 = 0.016013084;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var29 = 0.02769261;
                    } else {
                        var29 = 0.0014507909;
                    }
                }
            } else {
                if (input[6] < 10.2) {
                    if (input[6] < 10.1) {
                        var29 = -0.0035128817;
                    } else {
                        var29 = 0.018849373;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var29 = -0.0037795354;
                    } else {
                        var29 = -0.027458666;
                    }
                }
            }
        }
        double var30;
        if (input[3] < 2.0) {
            if (input[5] < 60.0) {
                if (input[2] < 1.0) {
                    if (input[5] < 24.0) {
                        var30 = 0.0019369593;
                    } else {
                        var30 = 0.0004396902;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var30 = 0.0017498661;
                    } else {
                        var30 = -0.011571455;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[4] < 1.0) {
                        var30 = 0.00094342895;
                    } else {
                        var30 = -0.0011514892;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var30 = 0.0011032084;
                    } else {
                        var30 = 0.014408876;
                    }
                }
            }
        } else {
            if (input[3] < 3.0) {
                if (input[4] < 1.0) {
                    if (input[2] < 2.0) {
                        var30 = 0.013936223;
                    } else {
                        var30 = -0.014552019;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var30 = -0.0030770458;
                    } else {
                        var30 = 0.0027747906;
                    }
                }
            } else {
                if (input[6] < 13.6) {
                    if (input[4] < 1.0) {
                        var30 = -0.008498788;
                    } else {
                        var30 = -0.003337678;
                    }
                } else {
                    var30 = 0.015511743;
                }
            }
        }
        double var31;
        if (input[3] < 2.0) {
            if (input[3] < 1.0) {
                if (input[6] < 5.3) {
                    if (input[4] < 1.0) {
                        var31 = 0.00029417276;
                    } else {
                        var31 = 0.01160982;
                    }
                } else {
                    if (input[6] < 5.4) {
                        var31 = -0.010126838;
                    } else {
                        var31 = 0.0012290349;
                    }
                }
            } else {
                if (input[6] < 8.1) {
                    if (input[2] < 1.0) {
                        var31 = -0.00035039298;
                    } else {
                        var31 = -0.013022022;
                    }
                } else {
                    if (input[6] < 9.4) {
                        var31 = -0.00012976766;
                    } else {
                        var31 = 0.0016314415;
                    }
                }
            }
        } else {
            if (input[2] < 1.0) {
                if (input[6] < 10.4) {
                    if (input[5] < 24.0) {
                        var31 = -0.0025082577;
                    } else {
                        var31 = 0.014674768;
                    }
                } else {
                    if (input[0] < 33.0) {
                        var31 = 0.018985499;
                    } else {
                        var31 = -0.0072502955;
                    }
                }
            } else {
                if (input[6] < 10.2) {
                    if (input[6] < 10.1) {
                        var31 = -0.0028723942;
                    } else {
                        var31 = 0.018331844;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var31 = -0.0033280086;
                    } else {
                        var31 = -0.024773244;
                    }
                }
            }
        }
        double var32;
        if (input[3] < 3.0) {
            if (input[7] < 2.0) {
                if (input[4] < 2.0) {
                    if (input[3] < 2.0) {
                        var32 = 0.00090922945;
                    } else {
                        var32 = 0.008300603;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var32 = 0.010561726;
                    } else {
                        var32 = 0.00037038975;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var32 = -0.0013516592;
                    } else {
                        var32 = 0.0019346451;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var32 = -0.0062907683;
                    } else {
                        var32 = 0.0047282763;
                    }
                }
            }
        } else {
            if (input[6] < 13.6) {
                if (input[4] < 1.0) {
                    if (input[5] < 6.0) {
                        var32 = -0.015337201;
                    } else {
                        var32 = 0.0053507225;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var32 = -0.002858834;
                    } else {
                        var32 = 0.0018955363;
                    }
                }
            } else {
                var32 = 0.013786879;
            }
        }
        double var33;
        if (input[3] < 3.0) {
            if (input[7] < 2.0) {
                if (input[4] < 2.0) {
                    if (input[3] < 2.0) {
                        var33 = 0.00081838755;
                    } else {
                        var33 = 0.007500188;
                    }
                } else {
                    if (input[0] < 22.0) {
                        var33 = -0.005817778;
                    } else {
                        var33 = 0.0082649095;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var33 = -0.0012171253;
                    } else {
                        var33 = 0.0017414906;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var33 = -0.0044361735;
                    } else {
                        var33 = 0.008435187;
                    }
                }
            }
        } else {
            if (input[6] < 13.6) {
                if (input[4] < 1.0) {
                    if (input[5] < 6.0) {
                        var33 = -0.013860285;
                    } else {
                        var33 = 0.0048453775;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var33 = -0.002573761;
                    } else {
                        var33 = 0.0017270433;
                    }
                }
            } else {
                var33 = 0.013097537;
            }
        }
        double var34;
        if (input[3] < 3.0) {
            if (input[4] < 1.0) {
                if (input[3] < 2.0) {
                    if (input[2] < 1.0) {
                        var34 = 0.00061094295;
                    } else {
                        var34 = 0.0013840421;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var34 = 0.011482253;
                    } else {
                        var34 = -0.01308108;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var34 = -0.002525741;
                    } else {
                        var34 = 0.0027354711;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var34 = -0.0069557377;
                    } else {
                        var34 = 0.0061849686;
                    }
                }
            }
        } else {
            if (input[6] < 13.6) {
                if (input[6] < 10.2) {
                    if (input[6] < 10.1) {
                        var34 = -0.002402105;
                    } else {
                        var34 = 0.016905447;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var34 = 0.0051572802;
                    } else {
                        var34 = -0.013302228;
                    }
                }
            } else {
                var34 = 0.0124426605;
            }
        }
        double var35;
        if (input[3] < 3.0) {
            if (input[7] < 2.0) {
                if (input[4] < 2.0) {
                    if (input[3] < 2.0) {
                        var35 = 0.00065330946;
                    } else {
                        var35 = 0.0063373917;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var35 = 0.009164479;
                    } else {
                        var35 = -0.0006997767;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var35 = -0.0009697581;
                    } else {
                        var35 = 0.0013870379;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var35 = -0.0037079442;
                    } else {
                        var35 = 0.007135392;
                    }
                }
            }
        } else {
            if (input[6] < 13.6) {
                if (input[4] < 1.0) {
                    if (input[5] < 6.0) {
                        var35 = -0.012018184;
                    } else {
                        var35 = 0.004763267;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var35 = -0.002077584;
                    } else {
                        var35 = 0.0017870506;
                    }
                }
            } else {
                var35 = 0.011820528;
            }
        }
        double var36;
        if (input[3] < 3.0) {
            if (input[4] < 1.0) {
                if (input[3] < 2.0) {
                    if (input[2] < 1.0) {
                        var36 = 0.00047703483;
                    } else {
                        var36 = 0.0012580082;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var36 = 0.010019399;
                    } else {
                        var36 = -0.012073036;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var36 = -0.0022697577;
                    } else {
                        var36 = 0.0023061014;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var36 = -0.005997616;
                    } else {
                        var36 = 0.0054129297;
                    }
                }
            }
        } else {
            if (input[6] < 13.6) {
                if (input[6] < 12.3) {
                    if (input[6] < 11.8) {
                        var36 = -0.0022045604;
                    } else {
                        var36 = 0.027688166;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var36 = -0.017092455;
                    } else {
                        var36 = 0.009695628;
                    }
                }
            } else {
                var36 = 0.0112295;
            }
        }
        double var37;
        if (input[3] < 3.0) {
            if (input[4] < 1.0) {
                if (input[3] < 2.0) {
                    if (input[5] < 24.0) {
                        var37 = 0.0009420804;
                    } else {
                        var37 = 0.00024006423;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var37 = 0.012051526;
                    } else {
                        var37 = -0.0031963026;
                    }
                }
            } else {
                if (input[7] < 2.0) {
                    if (input[3] < 2.0) {
                        var37 = 0.007206069;
                    } else {
                        var37 = -0.0015289685;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var37 = 0.00058469624;
                    } else {
                        var37 = -0.0030796488;
                    }
                }
            }
        } else {
            if (input[6] < 13.6) {
                if (input[4] < 1.0) {
                    if (input[5] < 6.0) {
                        var37 = -0.010428025;
                    } else {
                        var37 = 0.004139956;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var37 = -0.0016505824;
                    } else {
                        var37 = 0.0018241592;
                    }
                }
            } else {
                var37 = 0.010668024;
            }
        }
        double var38;
        if (input[3] < 3.0) {
            if (input[6] < 8.1) {
                if (input[2] < 1.0) {
                    if (input[5] < 24.0) {
                        var38 = 0.002164128;
                    } else {
                        var38 = -0.0014315015;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var38 = -0.004079491;
                    } else {
                        var38 = 0.008456207;
                    }
                }
            } else {
                if (input[6] < 8.3) {
                    if (input[7] < 3.0) {
                        var38 = -0.000015049994;
                    } else {
                        var38 = 0.0061345953;
                    }
                } else {
                    if (input[4] < 2.0) {
                        var38 = 0.00066394167;
                    } else {
                        var38 = -0.0018770591;
                    }
                }
            }
        } else {
            if (input[6] < 13.6) {
                if (input[6] < 10.2) {
                    if (input[6] < 10.1) {
                        var38 = -0.0015584202;
                    } else {
                        var38 = 0.015725244;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var38 = 0.005681252;
                    } else {
                        var38 = -0.010847165;
                    }
                }
            } else {
                var38 = 0.010134622;
            }
        }
        double var39;
        if (input[3] < 3.0) {
            if (input[1] < 1.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 2.0) {
                        var39 = 0.000536352;
                    } else {
                        var39 = 0.004955541;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var39 = 0.004095785;
                    } else {
                        var39 = -0.002610342;
                    }
                }
            } else {
                if (input[6] < 6.2) {
                    if (input[5] < 12.0) {
                        var39 = -0.0021572362;
                    } else {
                        var39 = 0.006481476;
                    }
                } else {
                    if (input[5] < 6.0) {
                        var39 = -0.0014857397;
                    } else {
                        var39 = 0.00072438695;
                    }
                }
            }
        } else {
            if (input[6] < 11.8) {
                if (input[6] < 11.5) {
                    if (input[6] < 10.9) {
                        var39 = -0.0016131118;
                    } else {
                        var39 = 0.0080829095;
                    }
                } else {
                    var39 = -0.020199904;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[0] < 24.0) {
                        var39 = 0.009627891;
                    } else {
                        var39 = -0.014137951;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var39 = 0.025075007;
                    } else {
                        var39 = 0.0027023107;
                    }
                }
            }
        }
        double var40;
        if (input[3] < 3.0) {
            if (input[6] < 7.0) {
                if (input[5] < 12.0) {
                    if (input[7] < 2.0) {
                        var40 = 0.0037100408;
                    } else {
                        var40 = -0.00504587;
                    }
                } else {
                    if (input[1] < 1.0) {
                        var40 = -0.0021965532;
                    } else {
                        var40 = 0.0032911263;
                    }
                }
            } else {
                if (input[6] < 7.1) {
                    if (input[0] < 20.0) {
                        var40 = -0.019828198;
                    } else {
                        var40 = 0.0059789782;
                    }
                } else {
                    if (input[6] < 9.4) {
                        var40 = -0.00009218632;
                    } else {
                        var40 = 0.00064060854;
                    }
                }
            }
        } else {
            if (input[6] < 11.8) {
                if (input[6] < 11.5) {
                    if (input[6] < 10.9) {
                        var40 = -0.0014522185;
                    } else {
                        var40 = 0.0073900893;
                    }
                } else {
                    var40 = -0.018684912;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[0] < 24.0) {
                        var40 = 0.009146497;
                    } else {
                        var40 = -0.012959788;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var40 = 0.023194382;
                    } else {
                        var40 = 0.002567196;
                    }
                }
            }
        }
        double var41;
        if (input[3] < 1.0) {
            if (input[5] < 12.0) {
                if (input[2] < 1.0) {
                    if (input[4] < 1.0) {
                        var41 = 0.00041745455;
                    } else {
                        var41 = 0.02015294;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var41 = 0.0017395398;
                    } else {
                        var41 = -0.026568694;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 24.0) {
                        var41 = 0.00095018477;
                    } else {
                        var41 = -0.000978407;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var41 = -0.0056124325;
                    } else {
                        var41 = 0.004152115;
                    }
                }
            }
        } else {
            if (input[6] < 9.6) {
                if (input[5] < 60.0) {
                    if (input[3] < 2.0) {
                        var41 = -0.0028448445;
                    } else {
                        var41 = -0.0006668164;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var41 = -0.0003707822;
                    } else {
                        var41 = 0.006771875;
                    }
                }
            } else {
                if (input[3] < 4.0) {
                    if (input[4] < 1.0) {
                        var41 = 0.0010131014;
                    } else {
                        var41 = 0.013380773;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var41 = -0.010517375;
                    } else {
                        var41 = 0.015275431;
                    }
                }
            }
        }
        double var42;
        if (input[3] < 3.0) {
            if (input[1] < 1.0) {
                if (input[4] < 1.0) {
                    if (input[5] < 6.0) {
                        var42 = 0.003491804;
                    } else {
                        var42 = 0.00039706836;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var42 = 0.0036417313;
                    } else {
                        var42 = -0.0022245857;
                    }
                }
            } else {
                if (input[6] < 6.2) {
                    if (input[5] < 12.0) {
                        var42 = -0.0015478054;
                    } else {
                        var42 = 0.005534738;
                    }
                } else {
                    if (input[5] < 6.0) {
                        var42 = -0.001263355;
                    } else {
                        var42 = 0.0005851552;
                    }
                }
            }
        } else {
            if (input[6] < 11.8) {
                if (input[6] < 11.5) {
                    if (input[6] < 10.9) {
                        var42 = -0.0012468996;
                    } else {
                        var42 = 0.0069992566;
                    }
                } else {
                    var42 = -0.017071264;
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[0] < 24.0) {
                        var42 = 0.008638516;
                    } else {
                        var42 = -0.0115798805;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var42 = 0.02137882;
                    } else {
                        var42 = 0.0016750664;
                    }
                }
            }
        }
        double var43;
        if (input[3] < 3.0) {
            if (input[1] < 1.0) {
                if (input[4] < 1.0) {
                    if (input[3] < 2.0) {
                        var43 = 0.0003714657;
                    } else {
                        var43 = 0.004184346;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var43 = 0.0004838739;
                    } else {
                        var43 = -0.0031230298;
                    }
                }
            } else {
                if (input[6] < 6.2) {
                    if (input[5] < 12.0) {
                        var43 = -0.0013997543;
                    } else {
                        var43 = 0.0049938434;
                    }
                } else {
                    if (input[5] < 6.0) {
                        var43 = -0.0011383349;
                    } else {
                        var43 = 0.0005266854;
                    }
                }
            }
        } else {
            if (input[4] < 1.0) {
                if (input[5] < 6.0) {
                    if (input[2] < 1.0) {
                        var43 = 0.0006140268;
                    } else {
                        var43 = -0.010303608;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var43 = -0.023576198;
                    } else {
                        var43 = 0.008302852;
                    }
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[2] < 1.0) {
                        var43 = -0.00092647754;
                    } else {
                        var43 = -0.00017646496;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var43 = -0.0056793895;
                    } else {
                        var43 = -0.0012659928;
                    }
                }
            }
        }
        double var44;
        if (input[3] < 3.0) {
            if (input[6] < 5.6) {
                if (input[4] < 2.0) {
                    if (input[6] < 5.5) {
                        var44 = 0.0047627157;
                    } else {
                        var44 = 0.025790432;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var44 = -0.0032137;
                    } else {
                        var44 = 0.0037100606;
                    }
                }
            } else {
                if (input[6] < 7.0) {
                    if (input[5] < 12.0) {
                        var44 = -0.0036386491;
                    } else {
                        var44 = 0.00021812633;
                    }
                } else {
                    if (input[6] < 7.1) {
                        var44 = 0.0035433825;
                    } else {
                        var44 = 0.00024763145;
                    }
                }
            }
        } else {
            if (input[6] < 11.8) {
                if (input[6] < 11.5) {
                    if (input[6] < 10.9) {
                        var44 = -0.0010268117;
                    } else {
                        var44 = 0.0064389417;
                    }
                } else {
                    if (input[0] < 22.0) {
                        var44 = -0.00682707;
                    } else {
                        var44 = -0.020726014;
                    }
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[0] < 24.0) {
                        var44 = 0.008175889;
                    } else {
                        var44 = -0.010066364;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var44 = 0.019152695;
                    } else {
                        var44 = 0.0011761695;
                    }
                }
            }
        }
        double var45;
        if (input[3] < 3.0) {
            if (input[6] < 5.6) {
                if (input[4] < 2.0) {
                    if (input[6] < 5.5) {
                        var45 = 0.0043230797;
                    } else {
                        var45 = 0.02385615;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var45 = -0.0029076335;
                    } else {
                        var45 = 0.003376158;
                    }
                }
            } else {
                if (input[6] < 7.0) {
                    if (input[6] < 6.7) {
                        var45 = 0.0001179202;
                    } else {
                        var45 = -0.0038700923;
                    }
                } else {
                    if (input[6] < 7.1) {
                        var45 = 0.0031986216;
                    } else {
                        var45 = 0.00022288023;
                    }
                }
            }
        } else {
            if (input[4] < 1.0) {
                if (input[5] < 6.0) {
                    if (input[2] < 1.0) {
                        var45 = 0.0003825598;
                    } else {
                        var45 = -0.009063083;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var45 = 0.015239756;
                    } else {
                        var45 = -0.006426175;
                    }
                }
            } else {
                if (input[5] < 24.0) {
                    if (input[6] < 9.5) {
                        var45 = -0.00038077298;
                    } else {
                        var45 = -0.0046158712;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var45 = -0.0040791337;
                    } else {
                        var45 = 0.0017857155;
                    }
                }
            }
        }
        double var46;
        if (input[3] < 1.0) {
            if (input[5] < 12.0) {
                if (input[2] < 1.0) {
                    if (input[4] < 1.0) {
                        var46 = 0.0002347998;
                    } else {
                        var46 = 0.018281266;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var46 = 0.0014327152;
                    } else {
                        var46 = -0.023788368;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[5] < 24.0) {
                        var46 = 0.0007246652;
                    } else {
                        var46 = -0.0010102126;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var46 = -0.005158192;
                    } else {
                        var46 = 0.003602865;
                    }
                }
            }
        } else {
            if (input[7] < 2.0) {
                if (input[4] < 3.0) {
                    if (input[3] < 4.0) {
                        var46 = 0.000659214;
                    } else {
                        var46 = -0.0034301206;
                    }
                } else {
                    if (input[6] < 7.1) {
                        var46 = 0.016403815;
                    } else {
                        var46 = -0.0038170125;
                    }
                }
            } else {
                if (input[5] < 60.0) {
                    if (input[6] < 9.6) {
                        var46 = -0.001539386;
                    } else {
                        var46 = 0.0014184775;
                    }
                } else {
                    if (input[6] < 8.4) {
                        var46 = 0.0071523185;
                    } else {
                        var46 = -0.0003801005;
                    }
                }
            }
        }
        double var47;
        if (input[1] < 1.0) {
            if (input[4] < 1.0) {
                if (input[3] < 3.0) {
                    if (input[5] < 6.0) {
                        var47 = 0.0029075735;
                    } else {
                        var47 = 0.00024714414;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var47 = -0.009656295;
                    } else {
                        var47 = 0.0023745482;
                    }
                }
            } else {
                if (input[3] < 2.0) {
                    if (input[2] < 1.0) {
                        var47 = 0.000118082346;
                    } else {
                        var47 = -0.0075075217;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var47 = 0.00038695356;
                    } else {
                        var47 = -0.0026136;
                    }
                }
            }
        } else {
            if (input[0] < 21.0) {
                if (input[3] < 1.0) {
                    if (input[6] < 5.3) {
                        var47 = 0.008101654;
                    } else {
                        var47 = 0.00035655947;
                    }
                } else {
                    if (input[6] < 8.2) {
                        var47 = -0.004814615;
                    } else {
                        var47 = -0.0012018817;
                    }
                }
            } else {
                if (input[5] < 6.0) {
                    if (input[0] < 29.0) {
                        var47 = 0.0012835461;
                    } else {
                        var47 = -0.002079002;
                    }
                } else {
                    if (input[6] < 6.2) {
                        var47 = 0.0028377043;
                    } else {
                        var47 = 0.00050308707;
                    }
                }
            }
        }
        double var48;
        if (input[1] < 1.0) {
            if (input[6] < 7.0) {
                if (input[3] < 1.0) {
                    if (input[5] < 12.0) {
                        var48 = 0.006891609;
                    } else {
                        var48 = -0.00022109605;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var48 = -0.007409931;
                    } else {
                        var48 = -0.00047876537;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    if (input[6] < 7.2) {
                        var48 = 0.0055573434;
                    } else {
                        var48 = 0.00020915331;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var48 = -0.0014352803;
                    } else {
                        var48 = 0.0008994232;
                    }
                }
            }
        } else {
            if (input[0] < 21.0) {
                if (input[3] < 1.0) {
                    if (input[4] < 3.0) {
                        var48 = 0.00017091124;
                    } else {
                        var48 = 0.00572208;
                    }
                } else {
                    if (input[6] < 8.4) {
                        var48 = -0.0043037343;
                    } else {
                        var48 = -0.0010453849;
                    }
                }
            } else {
                if (input[5] < 6.0) {
                    if (input[0] < 29.0) {
                        var48 = 0.0011565288;
                    } else {
                        var48 = -0.0018727649;
                    }
                } else {
                    if (input[6] < 6.7) {
                        var48 = 0.0018965462;
                    } else {
                        var48 = 0.00039304685;
                    }
                }
            }
        }
        double var49;
        if (input[6] < 4.1) {
            var49 = 0.008919928;
        } else {
            if (input[6] < 4.3) {
                var49 = -0.0103739835;
            } else {
                if (input[1] < 1.0) {
                    if (input[6] < 7.0) {
                        var49 = -0.0013841857;
                    } else {
                        var49 = -0.000034090535;
                    }
                } else {
                    if (input[0] < 21.0) {
                        var49 = -0.00092140195;
                    } else {
                        var49 = 0.00035709082;
                    }
                }
            }
        }
        double var50;
        if (input[6] < 4.1) {
            var50 = 0.008325265;
        } else {
            if (input[6] < 4.3) {
                var50 = -0.009855284;
            } else {
                if (input[7] < 2.0) {
                    if (input[6] < 7.2) {
                        var50 = 0.0033269988;
                    } else {
                        var50 = 0.00007386036;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var50 = 0.00038659884;
                    } else {
                        var50 = -0.0006654835;
                    }
                }
            }
        }
        double var51;
        if (input[6] < 4.1) {
            var51 = 0.0077702464;
        } else {
            if (input[6] < 4.3) {
                var51 = -0.009362521;
            } else {
                if (input[0] < 22.0) {
                    if (input[2] < 1.0) {
                        var51 = 0.0005355529;
                    } else {
                        var51 = -0.002044752;
                    }
                } else {
                    if (input[6] < 4.5) {
                        var51 = 0.009256959;
                    } else {
                        var51 = 0.0000949842;
                    }
                }
            }
        }
        double var52;
        if (input[1] < 1.0) {
            if (input[4] < 1.0) {
                if (input[3] < 3.0) {
                    if (input[5] < 24.0) {
                        var52 = 0.00069525273;
                    } else {
                        var52 = -0.00020926258;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var52 = 0.0039292774;
                    } else {
                        var52 = -0.008310426;
                    }
                }
            } else {
                if (input[3] < 2.0) {
                    if (input[2] < 1.0) {
                        var52 = 0.00017561966;
                    } else {
                        var52 = -0.006619431;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var52 = 0.0005479962;
                    } else {
                        var52 = -0.0022963178;
                    }
                }
            }
        } else {
            if (input[5] < 6.0) {
                if (input[0] < 29.0) {
                    if (input[6] < 10.3) {
                        var52 = -0.00018199113;
                    } else {
                        var52 = 0.006646991;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var52 = -0.0051737083;
                    } else {
                        var52 = -0.00084919523;
                    }
                }
            } else {
                if (input[0] < 29.0) {
                    if (input[7] < 3.0) {
                        var52 = 0.00023914842;
                    } else {
                        var52 = -0.0009037076;
                    }
                } else {
                    if (input[0] < 30.0) {
                        var52 = 0.003132565;
                    } else {
                        var52 = 0.0004957958;
                    }
                }
            }
        }
        double var53;
        if (input[6] < 5.6) {
            if (input[4] < 2.0) {
                if (input[6] < 5.5) {
                    if (input[7] < 2.0) {
                        var53 = 0.014233547;
                    } else {
                        var53 = 0.001674567;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var53 = 0.008478011;
                    } else {
                        var53 = 0.032308664;
                    }
                }
            } else {
                if (input[5] < 24.0) {
                    if (input[6] < 4.1) {
                        var53 = 0.00713681;
                    } else {
                        var53 = -0.0026296617;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var53 = -0.0019996087;
                    } else {
                        var53 = 0.008152251;
                    }
                }
            }
        } else {
            if (input[6] < 7.0) {
                if (input[6] < 6.7) {
                    if (input[5] < 12.0) {
                        var53 = -0.0012846299;
                    } else {
                        var53 = 0.0011977915;
                    }
                } else {
                    if (input[4] < 2.0) {
                        var53 = -0.00055371283;
                    } else {
                        var53 = -0.003917554;
                    }
                }
            } else {
                if (input[6] < 7.1) {
                    if (input[4] < 3.0) {
                        var53 = -0.00071569846;
                    } else {
                        var53 = 0.010689088;
                    }
                } else {
                    if (input[0] < 35.0) {
                        var53 = -0.00005679395;
                    } else {
                        var53 = 0.00051606976;
                    }
                }
            }
        }
        double var54;
        if (input[6] < 5.6) {
            if (input[4] < 2.0) {
                if (input[6] < 5.5) {
                    if (input[7] < 2.0) {
                        var54 = 0.0135218715;
                    } else {
                        var54 = 0.001519071;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var54 = 0.00791281;
                    } else {
                        var54 = 0.030693231;
                    }
                }
            } else {
                if (input[5] < 60.0) {
                    if (input[6] < 5.5) {
                        var54 = -0.0025397143;
                    } else {
                        var54 = 0.0018297723;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var54 = -0.0013670266;
                    } else {
                        var54 = 0.008288666;
                    }
                }
            }
        } else {
            if (input[6] < 6.1) {
                if (input[0] < 21.0) {
                    if (input[3] < 2.0) {
                        var54 = -0.014015063;
                    } else {
                        var54 = -0.00027207535;
                    }
                } else {
                    if (input[6] < 6.0) {
                        var54 = 0.00018702749;
                    } else {
                        var54 = -0.0036891946;
                    }
                }
            } else {
                if (input[6] < 6.2) {
                    if (input[4] < 3.0) {
                        var54 = -0.00034242214;
                    } else {
                        var54 = 0.014572597;
                    }
                } else {
                    if (input[6] < 7.0) {
                        var54 = -0.000727064;
                    } else {
                        var54 = 0.00008784394;
                    }
                }
            }
        }
        double var55;
        if (input[6] < 4.1) {
            var55 = 0.0069701574;
        } else {
            if (input[6] < 4.3) {
                var55 = -0.008660715;
            } else {
                if (input[6] < 4.7) {
                    if (input[4] < 3.0) {
                        var55 = 0.0058075543;
                    } else {
                        var55 = -0.0035951496;
                    }
                } else {
                    if (input[1] < 1.0) {
                        var55 = -0.00022574437;
                    } else {
                        var55 = 0.0001742282;
                    }
                }
            }
        }
        double var56;
        if (input[6] < 5.6) {
            if (input[4] < 2.0) {
                if (input[6] < 5.5) {
                    if (input[7] < 2.0) {
                        var56 = 0.012837067;
                    } else {
                        var56 = 0.0012987669;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var56 = 0.007387006;
                    } else {
                        var56 = 0.029149858;
                    }
                }
            } else {
                if (input[5] < 60.0) {
                    if (input[6] < 4.1) {
                        var56 = 0.0065584453;
                    } else {
                        var56 = -0.0017704265;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var56 = -0.0012873888;
                    } else {
                        var56 = 0.007348915;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var56 = -0.0129845515;
                } else {
                    if (input[7] < 2.0) {
                        var56 = -0.0040351357;
                    } else {
                        var56 = -0.0005624328;
                    }
                }
            } else {
                if (input[0] < 21.0) {
                    if (input[6] < 5.9) {
                        var56 = -0.009729043;
                    } else {
                        var56 = -0.00055289204;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var56 = 0.00013243259;
                    } else {
                        var56 = -0.0009299563;
                    }
                }
            }
        }
        double var57;
        if (input[6] < 5.6) {
            if (input[4] < 2.0) {
                if (input[6] < 5.5) {
                    if (input[7] < 2.0) {
                        var57 = 0.012195215;
                    } else {
                        var57 = 0.0011781662;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var57 = 0.0068945396;
                    } else {
                        var57 = 0.027692366;
                    }
                }
            } else {
                if (input[5] < 24.0) {
                    if (input[6] < 4.1) {
                        var57 = 0.0062305243;
                    } else {
                        var57 = -0.0020519362;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var57 = -0.0019389332;
                    } else {
                        var57 = 0.007031878;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var57 = -0.012335325;
                } else {
                    if (input[7] < 2.0) {
                        var57 = -0.0037123256;
                    } else {
                        var57 = -0.00051322015;
                    }
                }
            } else {
                if (input[7] < 2.0) {
                    if (input[6] < 7.2) {
                        var57 = 0.003908634;
                    } else {
                        var57 = 0.00001836082;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var57 = 0.0004052091;
                    } else {
                        var57 = -0.0006585644;
                    }
                }
            }
        }
        double var58;
        if (input[6] < 5.6) {
            if (input[4] < 2.0) {
                if (input[6] < 5.5) {
                    if (input[7] < 2.0) {
                        var58 = 0.0115854535;
                    } else {
                        var58 = 0.0010687662;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var58 = 0.0064349035;
                    } else {
                        var58 = 0.026307745;
                    }
                }
            } else {
                if (input[5] < 60.0) {
                    if (input[6] < 5.5) {
                        var58 = -0.0020512552;
                    } else {
                        var58 = 0.0018146202;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var58 = -0.0011260748;
                    } else {
                        var58 = 0.0066892244;
                    }
                }
            }
        } else {
            if (input[6] < 7.0) {
                if (input[6] < 6.7) {
                    if (input[0] < 23.0) {
                        var58 = -0.0021651965;
                    } else {
                        var58 = 0.00073639146;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var58 = -0.0037307404;
                    } else {
                        var58 = -0.000428539;
                    }
                }
            } else {
                if (input[6] < 7.1) {
                    if (input[0] < 20.0) {
                        var58 = -0.012795507;
                    } else {
                        var58 = 0.0034534757;
                    }
                } else {
                    if (input[0] < 35.0) {
                        var58 = -0.000054739685;
                    } else {
                        var58 = 0.00045452462;
                    }
                }
            }
        }
        double var59;
        if (input[6] < 4.1) {
            var59 = 0.0060293656;
        } else {
            if (input[6] < 4.3) {
                var59 = -0.007933999;
            } else {
                if (input[6] < 5.6) {
                    if (input[4] < 2.0) {
                        var59 = 0.005737833;
                    } else {
                        var59 = -0.0005001695;
                    }
                } else {
                    if (input[6] < 6.1) {
                        var59 = -0.0012409067;
                    } else {
                        var59 = 0.000023005381;
                    }
                }
            }
        }
        double var60;
        if (input[6] < 11.9) {
            if (input[2] < 1.0) {
                if (input[5] < 24.0) {
                    if (input[3] < 1.0) {
                        var60 = 0.0023420106;
                    } else {
                        var60 = -0.0004832417;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var60 = -0.0012800982;
                    } else {
                        var60 = 0.0030918221;
                    }
                }
            } else {
                if (input[5] < 60.0) {
                    if (input[7] < 2.0) {
                        var60 = 0.000542383;
                    } else {
                        var60 = -0.0015142516;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var60 = -0.0014052661;
                    } else {
                        var60 = 0.007584166;
                    }
                }
            }
        } else {
            if (input[3] < 2.0) {
                if (input[7] < 2.0) {
                    if (input[2] < 1.0) {
                        var60 = -0.00011850952;
                    } else {
                        var60 = -0.00058887684;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var60 = -0.000101137164;
                    } else {
                        var60 = 0.0041139545;
                    }
                }
            } else {
                if (input[6] < 12.5) {
                    var60 = 0.02085354;
                } else {
                    if (input[0] < 24.0) {
                        var60 = 0.009304176;
                    } else {
                        var60 = -0.004015384;
                    }
                }
            }
        }
        double var61;
        if (input[1] < 1.0) {
            if (input[6] < 6.9) {
                if (input[6] < 6.7) {
                    if (input[4] < 3.0) {
                        var61 = 0.0003578105;
                    } else {
                        var61 = -0.0024870443;
                    }
                } else {
                    if (input[0] < 30.0) {
                        var61 = -0.006993767;
                    } else {
                        var61 = 0.0019266207;
                    }
                }
            } else {
                if (input[5] < 6.0) {
                    if (input[3] < 3.0) {
                        var61 = 0.0035567493;
                    } else {
                        var61 = -0.00069032906;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var61 = 0.00014021364;
                    } else {
                        var61 = -0.0008934446;
                    }
                }
            }
        } else {
            if (input[6] < 6.8) {
                if (input[5] < 12.0) {
                    if (input[3] < 2.0) {
                        var61 = -0.0048988885;
                    } else {
                        var61 = 0.0006048008;
                    }
                } else {
                    if (input[4] < 3.0) {
                        var61 = 0.00084411784;
                    } else {
                        var61 = 0.0071334876;
                    }
                }
            } else {
                if (input[6] < 7.0) {
                    if (input[4] < 3.0) {
                        var61 = -0.00023382712;
                    } else {
                        var61 = -0.010339527;
                    }
                } else {
                    if (input[6] < 7.1) {
                        var61 = 0.0030331947;
                    } else {
                        var61 = 0.00008944721;
                    }
                }
            }
        }
        double var62;
        if (input[6] < 4.1) {
            var62 = 0.005597651;
        } else {
            if (input[6] < 4.3) {
                var62 = -0.007268192;
            } else {
                if (input[0] < 22.0) {
                    if (input[2] < 1.0) {
                        var62 = 0.0005528123;
                    } else {
                        var62 = -0.0016303575;
                    }
                } else {
                    if (input[0] < 25.0) {
                        var62 = 0.00063217763;
                    } else {
                        var62 = -0.000046060457;
                    }
                }
            }
        }
        double var63;
        if (input[6] < 4.1) {
            var63 = 0.0052244742;
        } else {
            if (input[6] < 4.3) {
                var63 = -0.0069047823;
            } else {
                if (input[6] < 4.7) {
                    if (input[4] < 3.0) {
                        var63 = 0.0045775194;
                    } else {
                        var63 = -0.0033927262;
                    }
                } else {
                    if (input[6] < 11.9) {
                        var63 = -0.00006360706;
                    } else {
                        var63 = 0.0004302434;
                    }
                }
            }
        }
        double var64;
        if (input[1] < 1.0) {
            if (input[7] < 2.0) {
                if (input[4] < 2.0) {
                    if (input[3] < 3.0) {
                        var64 = 0.00009893959;
                    } else {
                        var64 = -0.003905973;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var64 = 0.0061944076;
                    } else {
                        var64 = -0.0024043533;
                    }
                }
            } else {
                if (input[3] < 2.0) {
                    if (input[3] < 1.0) {
                        var64 = 0.00027461976;
                    } else {
                        var64 = -0.003363258;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var64 = -0.016727982;
                    } else {
                        var64 = 0.00083043514;
                    }
                }
            }
        } else {
            if (input[6] < 6.8) {
                if (input[7] < 2.0) {
                    if (input[0] < 33.0) {
                        var64 = 0.008811508;
                    } else {
                        var64 = -0.00471547;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var64 = -0.00046559455;
                    } else {
                        var64 = 0.0027109606;
                    }
                }
            } else {
                if (input[6] < 7.0) {
                    if (input[4] < 3.0) {
                        var64 = -0.00020578146;
                    } else {
                        var64 = -0.0094012115;
                    }
                } else {
                    if (input[6] < 7.1) {
                        var64 = 0.0027508524;
                    } else {
                        var64 = 0.00007699833;
                    }
                }
            }
        }
        double var65;
        if (input[6] < 4.1) {
            var65 = 0.005003804;
        } else {
            if (input[6] < 4.3) {
                var65 = -0.0065362635;
            } else {
                if (input[6] < 9.6) {
                    if (input[5] < 60.0) {
                        var65 = -0.00027467895;
                    } else {
                        var65 = 0.0008284915;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var65 = 0.00012756899;
                    } else {
                        var65 = 0.0061394437;
                    }
                }
            }
        }
        double var66;
        if (input[6] < 4.1) {
            var66 = 0.0046702167;
        } else {
            if (input[6] < 4.3) {
                var66 = -0.0062094503;
            } else {
                if (input[6] < 4.5) {
                    var66 = 0.005803445;
                } else {
                    if (input[6] < 11.9) {
                        var66 = -0.00005604571;
                    } else {
                        var66 = 0.0003991108;
                    }
                }
            }
        }
        double var67;
        if (input[0] < 28.0) {
            if (input[0] < 26.0) {
                if (input[2] < 1.0) {
                    if (input[5] < 24.0) {
                        var67 = 0.0012463717;
                    } else {
                        var67 = -0.0006437623;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var67 = -0.0010145301;
                    } else {
                        var67 = 0.0022698112;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[6] < 6.2) {
                        var67 = 0.0126645835;
                    } else {
                        var67 = -0.00008661801;
                    }
                } else {
                    if (input[1] < 1.0) {
                        var67 = -0.003635287;
                    } else {
                        var67 = -0.0006743727;
                    }
                }
            }
        } else {
            if (input[3] < 2.0) {
                if (input[6] < 6.1) {
                    if (input[2] < 2.0) {
                        var67 = -0.0019376269;
                    } else {
                        var67 = -0.013317615;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var67 = -0.00009648146;
                    } else {
                        var67 = 0.0018743388;
                    }
                }
            } else {
                if (input[6] < 9.8) {
                    if (input[7] < 2.0) {
                        var67 = -0.0029370978;
                    } else {
                        var67 = 0.00072310236;
                    }
                } else {
                    if (input[0] < 30.0) {
                        var67 = 0.024947038;
                    } else {
                        var67 = 0.001352223;
                    }
                }
            }
        }
        double var68;
        if (input[6] < 5.6) {
            if (input[6] < 5.5) {
                if (input[6] < 5.4) {
                    if (input[5] < 60.0) {
                        var68 = 0.00013096454;
                    } else {
                        var68 = 0.005137078;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var68 = -0.0058471416;
                    } else {
                        var68 = 0.0006991946;
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[0] < 23.0) {
                        var68 = 0.006130532;
                    } else {
                        var68 = 0.024358986;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var68 = -0.0013234735;
                    } else {
                        var68 = 0.002015176;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var68 = -0.011170307;
                } else {
                    if (input[0] < 27.0) {
                        var68 = -0.0032016838;
                    } else {
                        var68 = -0.00031885903;
                    }
                }
            } else {
                if (input[0] < 21.0) {
                    if (input[6] < 6.5) {
                        var68 = -0.0039661857;
                    } else {
                        var68 = -0.00019967664;
                    }
                } else {
                    if (input[6] < 6.7) {
                        var68 = 0.0007089816;
                    } else {
                        var68 = -0.000019227567;
                    }
                }
            }
        }
        double var69;
        if (input[6] < 5.6) {
            if (input[6] < 5.5) {
                if (input[6] < 5.4) {
                    if (input[5] < 60.0) {
                        var69 = 0.000118264834;
                    } else {
                        var69 = 0.004726111;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var69 = -0.0053273975;
                    } else {
                        var69 = 0.00064675504;
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[0] < 23.0) {
                        var69 = 0.00572183;
                    } else {
                        var69 = 0.023141036;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var69 = -0.0012573004;
                    } else {
                        var69 = 0.0018360494;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var69 = -0.010611791;
                } else {
                    if (input[4] < 3.0) {
                        var69 = -0.0024290322;
                    } else {
                        var69 = 0.0005528391;
                    }
                }
            } else {
                if (input[0] < 21.0) {
                    if (input[6] < 5.9) {
                        var69 = -0.008298046;
                    } else {
                        var69 = -0.00036527144;
                    }
                } else {
                    if (input[6] < 6.7) {
                        var69 = 0.0006383316;
                    } else {
                        var69 = -0.000017306365;
                    }
                }
            }
        }
        double var70;
        if (input[3] < 2.0) {
            if (input[6] < 7.3) {
                if (input[7] < 2.0) {
                    if (input[2] < 1.0) {
                        var70 = -0.0010040778;
                    } else {
                        var70 = 0.015554448;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var70 = -0.0002559414;
                    } else {
                        var70 = -0.0057161944;
                    }
                }
            } else {
                if (input[5] < 6.0) {
                    if (input[6] < 8.0) {
                        var70 = 0.015894737;
                    } else {
                        var70 = 0.0010402448;
                    }
                } else {
                    if (input[6] < 7.6) {
                        var70 = 0.0014215504;
                    } else {
                        var70 = -0.00006791419;
                    }
                }
            }
        } else {
            if (input[6] < 11.9) {
                if (input[6] < 10.2) {
                    if (input[6] < 9.6) {
                        var70 = 0.00017805252;
                    } else {
                        var70 = 0.0075332406;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var70 = 0.0060920627;
                    } else {
                        var70 = -0.010541625;
                    }
                }
            } else {
                if (input[6] < 12.5) {
                    var70 = 0.019205553;
                } else {
                    if (input[0] < 24.0) {
                        var70 = 0.009120045;
                    } else {
                        var70 = -0.0039452226;
                    }
                }
            }
        }
        double var71;
        if (input[6] < 5.6) {
            if (input[6] < 5.5) {
                if (input[6] < 5.4) {
                    if (input[5] < 60.0) {
                        var71 = 0.00007884641;
                    } else {
                        var71 = 0.004696113;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var71 = -0.0047897217;
                    } else {
                        var71 = 0.0005848945;
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[0] < 23.0) {
                        var71 = 0.0057214545;
                    } else {
                        var71 = 0.021975083;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var71 = -0.0011816382;
                    } else {
                        var71 = 0.0016714841;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var71 = -0.009795393;
                } else {
                    if (input[0] < 27.0) {
                        var71 = -0.0028177577;
                    } else {
                        var71 = -0.000023205366;
                    }
                }
            } else {
                if (input[0] < 21.0) {
                    if (input[6] < 6.5) {
                        var71 = -0.0033616696;
                    } else {
                        var71 = -0.00014066193;
                    }
                } else {
                    if (input[0] < 25.0) {
                        var71 = 0.0004163659;
                    } else {
                        var71 = -0.00005604674;
                    }
                }
            }
        }
        double var72;
        if (input[6] < 5.6) {
            if (input[6] < 5.5) {
                if (input[6] < 5.4) {
                    if (input[3] < 2.0) {
                        var72 = 0.0021199987;
                    } else {
                        var72 = -0.0008593807;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var72 = -0.004363969;
                    } else {
                        var72 = 0.0005410274;
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[0] < 23.0) {
                        var72 = 0.005340024;
                    } else {
                        var72 = 0.020876328;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var72 = -0.0011225581;
                    } else {
                        var72 = 0.0015229075;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var72 = -0.009305622;
                } else {
                    if (input[4] < 3.0) {
                        var72 = -0.0020978174;
                    } else {
                        var72 = 0.000808543;
                    }
                }
            } else {
                if (input[6] < 5.8) {
                    if (input[0] < 28.0) {
                        var72 = 0.0036319387;
                    } else {
                        var72 = -0.0011101939;
                    }
                } else {
                    if (input[6] < 5.9) {
                        var72 = -0.0026276826;
                    } else {
                        var72 = 0.000003832348;
                    }
                }
            }
        }
        double var73;
        if (input[3] < 4.0) {
            if (input[3] < 2.0) {
                if (input[6] < 7.3) {
                    if (input[7] < 2.0) {
                        var73 = 0.0034731315;
                    } else {
                        var73 = -0.0016143864;
                    }
                } else {
                    if (input[5] < 6.0) {
                        var73 = 0.0026966499;
                    } else {
                        var73 = 0.000002897529;
                    }
                }
            } else {
                if (input[6] < 11.9) {
                    if (input[6] < 11.0) {
                        var73 = 0.00045636715;
                    } else {
                        var73 = -0.00369555;
                    }
                } else {
                    if (input[6] < 12.9) {
                        var73 = 0.016835704;
                    } else {
                        var73 = -0.0006918052;
                    }
                }
            }
        } else {
            if (input[6] < 10.4) {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var73 = -0.00008072465;
                    } else {
                        var73 = -0.004709845;
                    }
                } else {
                    if (input[6] < 9.9) {
                        var73 = 0.00071832817;
                    } else {
                        var73 = -0.0067361295;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    var73 = 0.014126715;
                } else {
                    if (input[0] < 26.0) {
                        var73 = -0.02355301;
                    } else {
                        var73 = -0.0013167061;
                    }
                }
            }
        }
        double var74;
        if (input[6] < 5.6) {
            if (input[4] < 2.0) {
                if (input[6] < 5.5) {
                    if (input[7] < 2.0) {
                        var74 = 0.009574714;
                    } else {
                        var74 = 0.00020531286;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var74 = 0.005091648;
                    } else {
                        var74 = 0.019809691;
                    }
                }
            } else {
                if (input[7] < 2.0) {
                    if (input[5] < 6.0) {
                        var74 = -0.0016135036;
                    } else {
                        var74 = -0.0048187613;
                    }
                } else {
                    if (input[0] < 20.0) {
                        var74 = 0.008615824;
                    } else {
                        var74 = -0.00027190687;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var74 = -0.0087596215;
                } else {
                    if (input[7] < 2.0) {
                        var74 = -0.0028328307;
                    } else {
                        var74 = -0.00001454241;
                    }
                }
            } else {
                if (input[0] < 35.0) {
                    if (input[6] < 6.3) {
                        var74 = 0.0013095514;
                    } else {
                        var74 = -0.00015062455;
                    }
                } else {
                    if (input[6] < 6.3) {
                        var74 = -0.0025149367;
                    } else {
                        var74 = 0.00044357628;
                    }
                }
            }
        }
        double var75;
        if (input[6] < 9.6) {
            if (input[3] < 1.0) {
                if (input[5] < 12.0) {
                    if (input[2] < 1.0) {
                        var75 = 0.008564396;
                    } else {
                        var75 = -0.009328233;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var75 = -0.002425641;
                    } else {
                        var75 = 0.0007866377;
                    }
                }
            } else {
                if (input[5] < 60.0) {
                    if (input[3] < 2.0) {
                        var75 = -0.0019957402;
                    } else {
                        var75 = 0.00014265139;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var75 = -0.0014793908;
                    } else {
                        var75 = 0.004816507;
                    }
                }
            }
        } else {
            if (input[4] < 1.0) {
                if (input[6] < 9.7) {
                    if (input[3] < 2.0) {
                        var75 = 0.0006456272;
                    } else {
                        var75 = 0.023861436;
                    }
                } else {
                    if (input[3] < 4.0) {
                        var75 = 0.00009902413;
                    } else {
                        var75 = -0.0062929513;
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[7] < 3.0) {
                        var75 = 0.024898052;
                    } else {
                        var75 = 0.000092538074;
                    }
                } else {
                    var75 = -0.002415956;
                }
            }
        }
        double var76;
        if (input[6] < 5.6) {
            if (input[6] < 5.5) {
                if (input[6] < 5.4) {
                    if (input[0] < 21.0) {
                        var76 = 0.0032504804;
                    } else {
                        var76 = -0.000110871275;
                    }
                } else {
                    if (input[0] < 38.0) {
                        var76 = -0.0013631462;
                    } else {
                        var76 = -0.006328521;
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[0] < 23.0) {
                        var76 = 0.004885254;
                    } else {
                        var76 = 0.018812075;
                    }
                } else {
                    if (input[3] < 3.0) {
                        var76 = 0.0019847415;
                    } else {
                        var76 = -0.0000798901;
                    }
                }
            }
        } else {
            if (input[6] < 6.1) {
                if (input[0] < 21.0) {
                    if (input[3] < 2.0) {
                        var76 = -0.0103663625;
                    } else {
                        var76 = 0.000888291;
                    }
                } else {
                    if (input[6] < 6.0) {
                        var76 = 0.00055030035;
                    } else {
                        var76 = -0.003192036;
                    }
                }
            } else {
                if (input[6] < 6.3) {
                    if (input[7] < 2.0) {
                        var76 = 0.011826525;
                    } else {
                        var76 = 0.00048237547;
                    }
                } else {
                    if (input[6] < 6.5) {
                        var76 = -0.0016441835;
                    } else {
                        var76 = 0.000016960823;
                    }
                }
            }
        }
        double var77;
        if (input[6] < 6.7) {
            if (input[5] < 12.0) {
                if (input[0] < 38.0) {
                    if (input[3] < 2.0) {
                        var77 = -0.004614485;
                    } else {
                        var77 = 0.00042131424;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var77 = 0.017648777;
                    } else {
                        var77 = 0.0035681494;
                    }
                }
            } else {
                if (input[0] < 23.0) {
                    if (input[5] < 60.0) {
                        var77 = -0.0032521542;
                    } else {
                        var77 = 0.001482878;
                    }
                } else {
                    if (input[6] < 6.1) {
                        var77 = -0.00057492935;
                    } else {
                        var77 = 0.0034321274;
                    }
                }
            }
        } else {
            if (input[6] < 7.0) {
                if (input[3] < 2.0) {
                    if (input[2] < 1.0) {
                        var77 = -0.00025075694;
                    } else {
                        var77 = -0.0080241505;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var77 = -0.005968033;
                    } else {
                        var77 = 0.0010417176;
                    }
                }
            } else {
                if (input[4] < 3.0) {
                    if (input[4] < 2.0) {
                        var77 = 0.00005080211;
                    } else {
                        var77 = -0.00055226806;
                    }
                } else {
                    if (input[6] < 7.1) {
                        var77 = 0.008940969;
                    } else {
                        var77 = 0.00032192198;
                    }
                }
            }
        }
        double var78;
        if (input[6] < 5.6) {
            if (input[4] < 2.0) {
                if (input[6] < 5.5) {
                    if (input[7] < 2.0) {
                        var78 = 0.009630591;
                    } else {
                        var78 = 0.00014945687;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var78 = 0.004867203;
                    } else {
                        var78 = 0.01790022;
                    }
                }
            } else {
                if (input[7] < 2.0) {
                    if (input[5] < 6.0) {
                        var78 = -0.0014526579;
                    } else {
                        var78 = -0.0043556034;
                    }
                } else {
                    if (input[0] < 20.0) {
                        var78 = 0.007825011;
                    } else {
                        var78 = -0.00016391021;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var78 = -0.008086761;
                } else {
                    if (input[4] < 3.0) {
                        var78 = -0.0018719409;
                    } else {
                        var78 = 0.0008798465;
                    }
                }
            } else {
                if (input[6] < 11.9) {
                    if (input[7] < 2.0) {
                        var78 = 0.00017269839;
                    } else {
                        var78 = -0.00020477509;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var78 = 0.00014909591;
                    } else {
                        var78 = 0.0050522997;
                    }
                }
            }
        }
        double var79;
        if (input[6] < 5.6) {
            if (input[6] < 5.5) {
                if (input[5] < 60.0) {
                    if (input[0] < 21.0) {
                        var79 = 0.0024471197;
                    } else {
                        var79 = -0.0013801808;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var79 = 0.00067029597;
                    } else {
                        var79 = 0.004730414;
                    }
                }
            } else {
                if (input[4] < 2.0) {
                    if (input[0] < 23.0) {
                        var79 = 0.004542723;
                    } else {
                        var79 = 0.017005209;
                    }
                } else {
                    if (input[3] < 3.0) {
                        var79 = 0.0019089353;
                    } else {
                        var79 = -0.00009409121;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var79 = -0.0076824236;
                } else {
                    if (input[0] < 27.0) {
                        var79 = -0.0023172896;
                    } else {
                        var79 = 0.00025846425;
                    }
                }
            } else {
                if (input[0] < 21.0) {
                    if (input[6] < 5.9) {
                        var79 = -0.006501636;
                    } else {
                        var79 = -0.00025982127;
                    }
                } else {
                    if (input[6] < 6.7) {
                        var79 = 0.0005854062;
                    } else {
                        var79 = -0.000028092458;
                    }
                }
            }
        }
        double var80;
        if (input[6] < 5.6) {
            if (input[4] < 2.0) {
                if (input[6] < 5.5) {
                    if (input[7] < 2.0) {
                        var80 = 0.009026706;
                    } else {
                        var80 = 0.00009609399;
                    }
                } else {
                    if (input[0] < 23.0) {
                        var80 = 0.0042398744;
                    } else {
                        var80 = 0.016154949;
                    }
                }
            } else {
                if (input[7] < 2.0) {
                    if (input[5] < 6.0) {
                        var80 = -0.001263802;
                    } else {
                        var80 = -0.003973288;
                    }
                } else {
                    if (input[0] < 20.0) {
                        var80 = 0.0073114038;
                    } else {
                        var80 = -0.00012403692;
                    }
                }
            }
        } else {
            if (input[6] < 5.7) {
                if (input[0] < 22.0) {
                    var80 = -0.007298302;
                } else {
                    if (input[7] < 2.0) {
                        var80 = -0.002416489;
                    } else {
                        var80 = 0.00012394221;
                    }
                }
            } else {
                if (input[3] < 4.0) {
                    if (input[3] < 2.0) {
                        var80 = -0.00005305643;
                    } else {
                        var80 = 0.00038581868;
                    }
                } else {
                    if (input[6] < 10.4) {
                        var80 = -0.00012603622;
                    } else {
                        var80 = -0.007784904;
                    }
                }
            }
        }
        double var81;
        if (input[6] < 4.7) {
            if (input[0] < 37.0) {
                if (input[0] < 33.0) {
                    if (input[0] < 32.0) {
                        var81 = 0.0033047379;
                    } else {
                        var81 = 0.0007857323;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var81 = -0.0057200543;
                    } else {
                        var81 = -0.00048764492;
                    }
                }
            } else {
                var81 = 0.005651102;
            }
        } else {
            if (input[6] < 5.1) {
                if (input[2] < 1.0) {
                    if (input[3] < 3.0) {
                        var81 = -0.004885045;
                    } else {
                        var81 = 0.00026901628;
                    }
                } else {
                    if (input[0] < 21.0) {
                        var81 = -0.00052247115;
                    } else {
                        var81 = 0.00044872356;
                    }
                }
            } else {
                if (input[6] < 5.6) {
                    if (input[4] < 2.0) {
                        var81 = 0.005110703;
                    } else {
                        var81 = -0.00047512623;
                    }
                } else {
                    if (input[6] < 6.1) {
                        var81 = -0.0006999722;
                    } else {
                        var81 = 0.000013255288;
                    }
                }
            }
        }
        double var82;
        if (input[1] < 1.0) {
            if (input[6] < 6.9) {
                if (input[3] < 1.0) {
                    if (input[0] < 38.0) {
                        var82 = 0.0016479796;
                    } else {
                        var82 = -0.008579219;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var82 = -0.0006419349;
                    } else {
                        var82 = -0.011063468;
                    }
                }
            } else {
                if (input[0] < 38.0) {
                    if (input[3] < 2.0) {
                        var82 = -0.00020492151;
                    } else {
                        var82 = 0.0005795116;
                    }
                } else {
                    if (input[6] < 8.7) {
                        var82 = 0.0044802944;
                    } else {
                        var82 = -0.00012825437;
                    }
                }
            }
        } else {
            if (input[2] < 2.0) {
                if (input[4] < 3.0) {
                    if (input[6] < 8.2) {
                        var82 = -0.0006538661;
                    } else {
                        var82 = 0.000117473945;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var82 = -0.001198117;
                    } else {
                        var82 = 0.0024544722;
                    }
                }
            } else {
                if (input[3] < 2.0) {
                    if (input[4] < 1.0) {
                        var82 = 0.0006279676;
                    } else {
                        var82 = 0.0041592172;
                    }
                } else {
                    if (input[6] < 10.0) {
                        var82 = 0.00008775738;
                    } else {
                        var82 = -0.007166821;
                    }
                }
            }
        }
        double var83;
        if (input[6] < 6.3) {
            if (input[0] < 34.0) {
                if (input[7] < 2.0) {
                    if (input[0] < 32.0) {
                        var83 = 0.00160072;
                    } else {
                        var83 = 0.0374587;
                    }
                } else {
                    if (input[4] < 2.0) {
                        var83 = 0.0019114664;
                    } else {
                        var83 = -0.00013647582;
                    }
                }
            } else {
                if (input[7] < 3.0) {
                    if (input[6] < 6.2) {
                        var83 = -0.0023777233;
                    } else {
                        var83 = -0.010661624;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var83 = -0.0008129584;
                    } else {
                        var83 = 0.01146131;
                    }
                }
            }
        } else {
            if (input[6] < 6.5) {
                if (input[0] < 35.0) {
                    if (input[3] < 2.0) {
                        var83 = -0.005520023;
                    } else {
                        var83 = -0.0001156337;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var83 = 0.0065262727;
                    } else {
                        var83 = -0.00038148672;
                    }
                }
            } else {
                if (input[6] < 6.7) {
                    if (input[7] < 3.0) {
                        var83 = 0.0039444324;
                    } else {
                        var83 = -0.0010832438;
                    }
                } else {
                    if (input[6] < 7.0) {
                        var83 = -0.0013529614;
                    } else {
                        var83 = 0.000033934546;
                    }
                }
            }
        }
        double var84;
        if (input[6] < 4.1) {
            var84 = 0.0036555945;
        } else {
            if (input[6] < 4.3) {
                var84 = -0.005038583;
            } else {
                if (input[6] < 4.7) {
                    if (input[4] < 3.0) {
                        var84 = 0.0036560365;
                    } else {
                        var84 = -0.0028999925;
                    }
                } else {
                    if (input[6] < 9.6) {
                        var84 = -0.000101426805;
                    } else {
                        var84 = 0.00013074785;
                    }
                }
            }
        }
        double var85;
        if (input[6] < 6.3) {
            if (input[0] < 34.0) {
                if (input[7] < 2.0) {
                    if (input[0] < 32.0) {
                        var85 = 0.00145912;
                    } else {
                        var85 = 0.03559084;
                    }
                } else {
                    if (input[4] < 2.0) {
                        var85 = 0.0017272177;
                    } else {
                        var85 = -0.00012169887;
                    }
                }
            } else {
                if (input[7] < 3.0) {
                    if (input[2] < 1.0) {
                        var85 = -0.000649118;
                    } else {
                        var85 = -0.00619233;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var85 = -0.0007206353;
                    } else {
                        var85 = 0.010515383;
                    }
                }
            }
        } else {
            if (input[6] < 6.5) {
                if (input[0] < 35.0) {
                    if (input[3] < 2.0) {
                        var85 = -0.004976616;
                    } else {
                        var85 = -0.000094547744;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var85 = 0.0059641;
                    } else {
                        var85 = -0.00034526814;
                    }
                }
            } else {
                if (input[6] < 6.7) {
                    if (input[7] < 3.0) {
                        var85 = 0.003568309;
                    } else {
                        var85 = -0.0009675966;
                    }
                } else {
                    if (input[6] < 7.0) {
                        var85 = -0.001208554;
                    } else {
                        var85 = 0.000028619688;
                    }
                }
            }
        }
        double var86;
        if (input[6] < 6.3) {
            if (input[0] < 34.0) {
                if (input[7] < 2.0) {
                    if (input[0] < 32.0) {
                        var86 = 0.0013213141;
                    } else {
                        var86 = 0.033811297;
                    }
                } else {
                    if (input[0] < 33.0) {
                        var86 = 0.00041594115;
                    } else {
                        var86 = 0.005072629;
                    }
                }
            } else {
                if (input[3] < 2.0) {
                    if (input[2] < 2.0) {
                        var86 = -0.0018334102;
                    } else {
                        var86 = -0.016614746;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var86 = -0.0030329775;
                    } else {
                        var86 = 0.0008248715;
                    }
                }
            }
        } else {
            if (input[6] < 6.5) {
                if (input[0] < 35.0) {
                    if (input[3] < 2.0) {
                        var86 = -0.004495542;
                    } else {
                        var86 = -0.00008536318;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var86 = -0.00058891176;
                    } else {
                        var86 = 0.0055770106;
                    }
                }
            } else {
                if (input[6] < 6.7) {
                    if (input[7] < 3.0) {
                        var86 = 0.003219071;
                    } else {
                        var86 = -0.0008731402;
                    }
                } else {
                    if (input[6] < 7.0) {
                        var86 = -0.0010885561;
                    } else {
                        var86 = 0.000025759653;
                    }
                }
            }
        }
        double var87;
        if (input[6] < 4.1) {
            if (input[0] < 33.0) {
                var87 = 0.00091410876;
            } else {
                var87 = 0.0042206915;
            }
        } else {
            if (input[6] < 4.3) {
                var87 = -0.004658952;
            } else {
                if (input[6] < 4.7) {
                    if (input[4] < 3.0) {
                        var87 = 0.003320882;
                    } else {
                        var87 = -0.0026308657;
                    }
                } else {
                    if (input[6] < 5.1) {
                        var87 = -0.002150092;
                    } else {
                        var87 = 0.0000059664467;
                    }
                }
            }
        }
        double var88;
        if (input[6] < 11.9) {
            if (input[2] < 1.0) {
                if (input[5] < 24.0) {
                    if (input[3] < 1.0) {
                        var88 = 0.0019261235;
                    } else {
                        var88 = -0.00034876904;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var88 = -0.0011436781;
                    } else {
                        var88 = 0.002586633;
                    }
                }
            } else {
                if (input[5] < 60.0) {
                    if (input[5] < 12.0) {
                        var88 = 0.00029462346;
                    } else {
                        var88 = -0.0015879633;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var88 = -0.0014032073;
                    } else {
                        var88 = 0.0063613914;
                    }
                }
            }
        } else {
            if (input[3] < 2.0) {
                if (input[7] < 2.0) {
                    if (input[1] < 1.0) {
                        var88 = -0.00018961918;
                    } else {
                        var88 = -0.0005242871;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var88 = 0.0026115596;
                    } else {
                        var88 = -0.000970337;
                    }
                }
            } else {
                if (input[6] < 12.9) {
                    if (input[2] < 2.0) {
                        var88 = 0.014986686;
                    } else {
                        var88 = 0.0015080818;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var88 = 0.007201725;
                    } else {
                        var88 = -0.006315235;
                    }
                }
            }
        }
        double var89;
        if (input[2] < 2.0) {
            if (input[0] < 25.0) {
                if (input[5] < 24.0) {
                    if (input[4] < 2.0) {
                        var89 = 0.00020028911;
                    } else {
                        var89 = 0.0022986846;
                    }
                } else {
                    if (input[6] < 7.7) {
                        var89 = -0.0040664007;
                    } else {
                        var89 = 0.0005197771;
                    }
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var89 = 0.0012324136;
                    } else {
                        var89 = -0.001357734;
                    }
                } else {
                    if (input[6] < 6.7) {
                        var89 = 0.0016143402;
                    } else {
                        var89 = -0.00009013232;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 2.0) {
                    if (input[6] < 6.5) {
                        var89 = -0.0027657028;
                    } else {
                        var89 = 0.0012295637;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var89 = -0.008331345;
                    } else {
                        var89 = 0.00025659715;
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[7] < 2.0) {
                        var89 = -0.0007440842;
                    } else {
                        var89 = -0.0025739528;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var89 = -0.0017138124;
                    } else {
                        var89 = 0.007181956;
                    }
                }
            }
        }
        double var90;
        if (input[3] < 2.0) {
            if (input[0] < 20.0) {
                if (input[3] < 1.0) {
                    if (input[6] < 7.2) {
                        var90 = 0.0077706696;
                    } else {
                        var90 = -0.0009287742;
                    }
                } else {
                    if (input[6] < 7.1) {
                        var90 = -0.016594648;
                    } else {
                        var90 = 0.00058648176;
                    }
                }
            } else {
                if (input[2] < 2.0) {
                    if (input[0] < 23.0) {
                        var90 = 0.00057644414;
                    } else {
                        var90 = -0.00022831753;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var90 = -0.00054801063;
                    } else {
                        var90 = 0.0027039873;
                    }
                }
            }
        } else {
            if (input[6] < 11.9) {
                if (input[6] < 10.2) {
                    if (input[6] < 9.6) {
                        var90 = 0.00011648432;
                    } else {
                        var90 = 0.006508128;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var90 = 0.0047669723;
                    } else {
                        var90 = -0.010158677;
                    }
                }
            } else {
                if (input[6] < 12.5) {
                    var90 = 0.01487939;
                } else {
                    if (input[5] < 12.0) {
                        var90 = -0.0036918838;
                    } else {
                        var90 = 0.005917278;
                    }
                }
            }
        }
        double var91;
        if (input[3] < 4.0) {
            if (input[3] < 2.0) {
                if (input[6] < 7.3) {
                    if (input[7] < 2.0) {
                        var91 = 0.0031974034;
                    } else {
                        var91 = -0.0012655723;
                    }
                } else {
                    if (input[5] < 6.0) {
                        var91 = 0.0023048983;
                    } else {
                        var91 = -0.0000137516245;
                    }
                }
            } else {
                if (input[6] < 11.9) {
                    if (input[0] < 28.0) {
                        var91 = -0.000410869;
                    } else {
                        var91 = 0.0008001681;
                    }
                } else {
                    if (input[6] < 12.9) {
                        var91 = 0.012766428;
                    } else {
                        var91 = -0.00085202727;
                    }
                }
            }
        } else {
            if (input[6] < 10.4) {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var91 = 0.00008759691;
                    } else {
                        var91 = -0.004408687;
                    }
                } else {
                    if (input[6] < 9.7) {
                        var91 = 0.00066585944;
                    } else {
                        var91 = -0.0053793727;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    var91 = 0.013863889;
                } else {
                    if (input[0] < 26.0) {
                        var91 = -0.020849366;
                    } else {
                        var91 = -0.00019441266;
                    }
                }
            }
        }
        double var92;
        if (input[1] < 1.0) {
            if (input[2] < 1.0) {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var92 = 0.0031576473;
                    } else {
                        var92 = -0.0041446122;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var92 = -0.00031924274;
                    } else {
                        var92 = 0.0025349848;
                    }
                }
            } else {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var92 = -0.0047698175;
                    } else {
                        var92 = 0.0016905643;
                    }
                } else {
                    if (input[3] < 1.0) {
                        var92 = 0.00065140973;
                    } else {
                        var92 = -0.0031332306;
                    }
                }
            }
        } else {
            if (input[6] < 6.9) {
                if (input[5] < 12.0) {
                    if (input[0] < 38.0) {
                        var92 = -0.0008044722;
                    } else {
                        var92 = 0.0063410737;
                    }
                } else {
                    if (input[4] < 3.0) {
                        var92 = 0.00056376023;
                    } else {
                        var92 = 0.0045339726;
                    }
                }
            } else {
                if (input[6] < 7.2) {
                    if (input[4] < 2.0) {
                        var92 = 0.0027646206;
                    } else {
                        var92 = -0.0036495675;
                    }
                } else {
                    if (input[7] < 2.0) {
                        var92 = -0.0002270669;
                    } else {
                        var92 = 0.00040380983;
                    }
                }
            }
        }
        double var93;
        if (input[6] < 6.3) {
            if (input[0] < 34.0) {
                if (input[7] < 2.0) {
                    if (input[0] < 32.0) {
                        var93 = 0.0011722569;
                    } else {
                        var93 = 0.031784624;
                    }
                } else {
                    if (input[4] < 2.0) {
                        var93 = 0.0015328276;
                    } else {
                        var93 = -0.00013977478;
                    }
                }
            } else {
                if (input[7] < 3.0) {
                    if (input[3] < 2.0) {
                        var93 = -0.005279481;
                    } else {
                        var93 = 0.00051288115;
                    }
                } else {
                    if (input[5] < 24.0) {
                        var93 = -0.0005279684;
                    } else {
                        var93 = 0.009647779;
                    }
                }
            }
        } else {
            if (input[6] < 6.5) {
                if (input[0] < 21.0) {
                    if (input[5] < 60.0) {
                        var93 = -0.010142533;
                    } else {
                        var93 = 0.0039715245;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var93 = -0.001835877;
                    } else {
                        var93 = 0.0015259798;
                    }
                }
            } else {
                if (input[6] < 6.7) {
                    if (input[0] < 37.0) {
                        var93 = 0.00043079155;
                    } else {
                        var93 = 0.0065017254;
                    }
                } else {
                    if (input[6] < 7.0) {
                        var93 = -0.0008829247;
                    } else {
                        var93 = 0.000018388457;
                    }
                }
            }
        }
        double var94;
        if (input[3] < 4.0) {
            if (input[3] < 2.0) {
                if (input[0] < 20.0) {
                    if (input[4] < 1.0) {
                        var94 = 0.00023490761;
                    } else {
                        var94 = -0.0035702698;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var94 = -0.000086594;
                    } else {
                        var94 = 0.00089106866;
                    }
                }
            } else {
                if (input[6] < 11.9) {
                    if (input[6] < 11.0) {
                        var94 = 0.0003379458;
                    } else {
                        var94 = -0.0029326465;
                    }
                } else {
                    if (input[6] < 12.9) {
                        var94 = 0.011839702;
                    } else {
                        var94 = -0.0008275557;
                    }
                }
            }
        } else {
            if (input[6] < 10.4) {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var94 = 0.00023969138;
                    } else {
                        var94 = -0.004039698;
                    }
                } else {
                    if (input[6] < 9.9) {
                        var94 = 0.00057085266;
                    } else {
                        var94 = -0.0059711183;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    var94 = 0.0130430255;
                } else {
                    if (input[0] < 26.0) {
                        var94 = -0.019245299;
                    } else {
                        var94 = -0.00017995332;
                    }
                }
            }
        }
        double var95;
        if (input[2] < 1.0) {
            if (input[5] < 24.0) {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var95 = 0.002802358;
                    } else {
                        var95 = -0.0022434432;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var95 = -0.00070485653;
                    } else {
                        var95 = 0.004624204;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[4] < 1.0) {
                        var95 = 0.00009119262;
                    } else {
                        var95 = -0.0032071155;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var95 = -0.0007742704;
                    } else {
                        var95 = 0.00671773;
                    }
                }
            }
        } else {
            if (input[5] < 60.0) {
                if (input[4] < 1.0) {
                    if (input[5] < 6.0) {
                        var95 = -0.005055069;
                    } else {
                        var95 = 0.0012406458;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var95 = -0.0056180083;
                    } else {
                        var95 = 0.00022783724;
                    }
                }
            } else {
                if (input[4] < 1.0) {
                    if (input[7] < 2.0) {
                        var95 = -0.000807606;
                    } else {
                        var95 = -0.0022536276;
                    }
                } else {
                    if (input[3] < 2.0) {
                        var95 = 0.0066250497;
                    } else {
                        var95 = -0.000002294353;
                    }
                }
            }
        }
        double var96;
        if (input[2] < 1.0) {
            if (input[5] < 24.0) {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var96 = 0.0025236197;
                    } else {
                        var96 = -0.0020197935;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var96 = -0.0006345498;
                    } else {
                        var96 = 0.004164062;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[4] < 1.0) {
                        var96 = 0.000082093175;
                    } else {
                        var96 = -0.0028882714;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var96 = -0.00069789024;
                    } else {
                        var96 = 0.006062751;
                    }
                }
            }
        } else {
            if (input[5] < 24.0) {
                if (input[3] < 1.0) {
                    if (input[4] < 1.0) {
                        var96 = 0.0021845144;
                    } else {
                        var96 = -0.021098502;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var96 = 0.0010067143;
                    } else {
                        var96 = -0.00086350105;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[4] < 1.0) {
                        var96 = -0.001134849;
                    } else {
                        var96 = 0.010409829;
                    }
                } else {
                    if (input[6] < 8.1) {
                        var96 = -0.009743917;
                    } else {
                        var96 = 0.0014599635;
                    }
                }
            }
        }
        double var97;
        if (input[3] < 2.0) {
            if (input[0] < 20.0) {
                if (input[3] < 1.0) {
                    if (input[6] < 7.2) {
                        var97 = 0.006517876;
                    } else {
                        var97 = -0.0008189456;
                    }
                } else {
                    if (input[6] < 8.2) {
                        var97 = -0.012835043;
                    } else {
                        var97 = 0.0011210974;
                    }
                }
            } else {
                if (input[2] < 2.0) {
                    if (input[0] < 23.0) {
                        var97 = 0.0005257106;
                    } else {
                        var97 = -0.0001982419;
                    }
                } else {
                    if (input[4] < 2.0) {
                        var97 = 0.0014157882;
                    } else {
                        var97 = -0.002274895;
                    }
                }
            }
        } else {
            if (input[6] < 11.9) {
                if (input[6] < 10.2) {
                    if (input[6] < 9.6) {
                        var97 = 0.0000782509;
                    } else {
                        var97 = 0.0059478306;
                    }
                } else {
                    if (input[2] < 1.0) {
                        var97 = 0.006434084;
                    } else {
                        var97 = -0.007935989;
                    }
                }
            } else {
                if (input[6] < 12.5) {
                    var97 = 0.012040689;
                } else {
                    if (input[2] < 1.0) {
                        var97 = 0.006956402;
                    } else {
                        var97 = -0.0022879038;
                    }
                }
            }
        }
        double var98;
        if (input[3] < 4.0) {
            if (input[3] < 2.0) {
                if (input[0] < 20.0) {
                    if (input[6] < 7.1) {
                        var98 = -0.004325575;
                    } else {
                        var98 = -0.00016633346;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var98 = -0.00007425446;
                    } else {
                        var98 = 0.00066395494;
                    }
                }
            } else {
                if (input[6] < 10.9) {
                    if (input[6] < 10.6) {
                        var98 = 0.00026912088;
                    } else {
                        var98 = -0.020543564;
                    }
                } else {
                    if (input[2] < 2.0) {
                        var98 = 0.0070956685;
                    } else {
                        var98 = -0.012992039;
                    }
                }
            }
        } else {
            if (input[6] < 10.4) {
                if (input[2] < 1.0) {
                    if (input[5] < 12.0) {
                        var98 = 0.00057678315;
                    } else {
                        var98 = -0.004474414;
                    }
                } else {
                    if (input[6] < 9.7) {
                        var98 = 0.00047565927;
                    } else {
                        var98 = -0.004320039;
                    }
                }
            } else {
                if (input[2] < 1.0) {
                    var98 = 0.012136141;
                } else {
                    if (input[0] < 26.0) {
                        var98 = -0.017422944;
                    } else {
                        var98 = 0.00036859847;
                    }
                }
            }
        }
        double var99;
        if (input[2] < 1.0) {
            if (input[5] < 24.0) {
                if (input[5] < 12.0) {
                    if (input[3] < 1.0) {
                        var99 = 0.002287059;
                    } else {
                        var99 = -0.0018443589;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var99 = -0.0005720489;
                    } else {
                        var99 = 0.0037913618;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[4] < 1.0) {
                        var99 = 0.00009293902;
                    } else {
                        var99 = -0.002586435;
                    }
                } else {
                    if (input[4] < 1.0) {
                        var99 = -0.00061300985;
                    } else {
                        var99 = 0.005523225;
                    }
                }
            }
        } else {
            if (input[5] < 24.0) {
                if (input[3] < 1.0) {
                    if (input[4] < 1.0) {
                        var99 = 0.001930568;
                    } else {
                        var99 = -0.01901297;
                    }
                } else {
                    if (input[5] < 12.0) {
                        var99 = 0.0009009664;
                    } else {
                        var99 = -0.00080503634;
                    }
                }
            } else {
                if (input[3] < 1.0) {
                    if (input[4] < 1.0) {
                        var99 = -0.0010677007;
                    } else {
                        var99 = 0.00938112;
                    }
                } else {
                    if (input[5] < 60.0) {
                        var99 = -0.007932342;
                    } else {
                        var99 = 0.0023369046;
                    }
                }
            }
        }
        return 0.7 + (var0 + var1 + var2 + var3 + var4 + var5 + var6 + var7 + var8 + var9 + var10 + var11 + var12 + var13 + var14 + var15 + var16 + var17 + var18 + var19 + var20 + var21 + var22 + var23 + var24 + var25 + var26 + var27 + var28 + var29 + var30 + var31 + var32 + var33 + var34 + var35 + var36 + var37 + var38 + var39 + var40 + var41 + var42 + var43 + var44 + var45 + var46 + var47 + var48 + var49 + var50 + var51 + var52 + var53 + var54 + var55 + var56 + var57 + var58 + var59 + var60 + var61 + var62 + var63 + var64 + var65 + var66 + var67 + var68 + var69 + var70 + var71 + var72 + var73 + var74 + var75 + var76 + var77 + var78 + var79 + var80 + var81 + var82 + var83 + var84 + var85 + var86 + var87 + var88 + var89 + var90 + var91 + var92 + var93 + var94 + var95 + var96 + var97 + var98 + var99);
    }
}
