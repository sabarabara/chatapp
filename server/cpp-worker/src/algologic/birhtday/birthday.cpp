#include <bits/stdc++.h>
#include "birthday.h"
using ll = long long;
using namespace std;

void birthday(const UserData& user, Attributes& atr) {
    try {
        string birth = user.birthday;
        int month = std::stoi(birth.substr(5, 2));


        if (month >= 3 && month <= 5) {
            // 春
            atr.BrainMuscle += 10;
            atr.AgilityWeight += 0;
            atr.OffenseDefense += -5;
            atr.IndependenceCooperation += 5;
            atr.DarknessLight += -5;
            atr.TemperanceEnthusiasm += -10;
            atr.NatureTechnology += 15;
            atr.PastFuture += -5;
        } else if (month >= 6 && month <= 8) {
            // 夏
            atr.BrainMuscle += 0;
            atr.AgilityWeight += 10;
            atr.OffenseDefense += 10;
            atr.IndependenceCooperation += 0;
            atr.DarknessLight += 5;
            atr.TemperanceEnthusiasm += 15;
            atr.NatureTechnology += -10;
            atr.PastFuture += 0;
        } else if (month >= 9 && month <= 11) {
            // 秋
            atr.BrainMuscle += 5;
            atr.AgilityWeight += -10;
            atr.OffenseDefense += 0;
            atr.IndependenceCooperation += -5;
            atr.DarknessLight += 10;
            atr.TemperanceEnthusiasm += 0;
            atr.NatureTechnology += -5;
            atr.PastFuture += 0;
        } else if(month == 12 || month ==1 || month == 2) {
            // 冬
            atr.BrainMuscle += -5;
            atr.AgilityWeight += 0;
            atr.OffenseDefense += 5;
            atr.IndependenceCooperation += 0;
            atr.DarknessLight += 15;
            atr.TemperanceEnthusiasm += -5;
            atr.NatureTechnology += 0;
            atr.PastFuture += 10;
        }
        else{
          throw runtime_error("maybe bithday-format is incorrect");
        }

    } catch (const exception& e) {
        cerr << "Error parsing birthday: " << e.what() << endl;
    }
}
