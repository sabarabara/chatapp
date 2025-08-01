#include <bits/stdc++.h>
#include "favoriteweather.h"
using ll = long long;
using namespace std;

void favoriteweather(const UserData& user, Attributes& atr) {
    try {
        string weather = user.favorite_weather;

        if (weather == "sunny") {
            atr.BrainMuscle += 10;
            atr.AgilityWeight += 5;
            atr.OffenseDefense += 10;
            atr.IndependenceCooperation += 10;
            atr.DarknessLight += 0;
            atr.TemperanceEnthusiasm += 5;
            atr.NatureTechnology += 0;
            atr.PastFuture += 0;
        } else if (weather == "cloudy") {
            atr.BrainMuscle += 0;
            atr.AgilityWeight += 0;
            atr.OffenseDefense += 0;
            atr.IndependenceCooperation += 0;
            atr.DarknessLight += 5;
            atr.TemperanceEnthusiasm += 0;
            atr.NatureTechnology += 5;
            atr.PastFuture += 0;
        } else if (weather == "rainy") {
            atr.BrainMuscle += -10;
            atr.AgilityWeight += -5;
            atr.OffenseDefense += -10;
            atr.IndependenceCooperation += -5;
            atr.DarknessLight += 10;
            atr.TemperanceEnthusiasm += -5;
            atr.NatureTechnology += 10;
            atr.PastFuture += 5;
        } else {
            throw std::runtime_error("Invalid favorite weather");
        }

    } catch (const std::exception& e) {
        cerr << "Error caught: " << e.what() << endl;
    }
}
