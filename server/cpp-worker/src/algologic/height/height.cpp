#include <bits/stdc++.h>
#include "height.h"
using ll = long long;
using namespace std;



void height(const UserData& user, Attributes& atr) {
    try {
        const double height = user.height;
        const double center_height = 171.25;
        const double standard_deviation = 6.0;
        const double Height_deviation_value = (height-center_height)*10/standard_deviation;

        if (Height_deviation_value > 50.0) {
            atr.BrainMuscle += 10;
            atr.AgilityWeight += 15;
            atr.OffenseDefense += -5;
            atr.IndependenceCooperation += 0;
            atr.DarknessLight += 0;
            atr.TemperanceEnthusiasm += -5;
            atr.NatureTechnology += 5;
            atr.PastFuture += 0;
        } else if(Height_deviation_value < 50.0) {
            atr.BrainMuscle += -10;
            atr.AgilityWeight += -15;
            atr.OffenseDefense += 10;
            atr.IndependenceCooperation += 5;
            atr.DarknessLight += 0;
            atr.TemperanceEnthusiasm += 5;
            atr.NatureTechnology += 0;
            atr.PastFuture += 5;
        }
        else{
          throw std::runtime_error("Invalid height: must be tyoe num");
        }
    } catch (const std::exception& e) {
        cerr << "Error caught: " << e.what() << endl;
    }
}
