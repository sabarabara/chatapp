#include <bits/stdc++.h>
#include "favoritecolor.h"
using ll = long long;
using namespace std;

void favoritecolor(const UserData& user, Attributes& atr) {
    try {
        string favocolor = user.favorite_color;

        if (favocolor == "red") {
            atr.BrainMuscle += -10;
            atr.AgilityWeight += 15;
            atr.OffenseDefense += 20;
            atr.IndependenceCooperation += 0;
            atr.DarknessLight += -5;
            atr.TemperanceEnthusiasm += 15;
            atr.NatureTechnology += 0;
            atr.PastFuture += 0;
        } else if (favocolor == "blue") {
            atr.BrainMuscle += 15;
            atr.AgilityWeight += 0;
            atr.OffenseDefense += 0;
            atr.IndependenceCooperation += 0;
            atr.DarknessLight += 0;
            atr.TemperanceEnthusiasm += -10;
            atr.NatureTechnology += 0;
            atr.PastFuture += 15;
        } else if (favocolor == "green") {
            atr.BrainMuscle += 0;
            atr.AgilityWeight += 5;
            atr.OffenseDefense += 0;
            atr.IndependenceCooperation += 10;
            atr.DarknessLight += -5;
            atr.TemperanceEnthusiasm += 0;
            atr.NatureTechnology += 15;
            atr.PastFuture += 0;
        } else if (favocolor == "yellow") {
            atr.BrainMuscle += 5;
            atr.AgilityWeight += 10;
            atr.OffenseDefense += 5;
            atr.IndependenceCooperation += -5;
            atr.DarknessLight += 0;
            atr.TemperanceEnthusiasm += 5;
            atr.NatureTechnology += -10;
            atr.PastFuture += 0;
        } else if (favocolor == "purple") {
            atr.BrainMuscle += 10;
            atr.AgilityWeight += -5;
            atr.OffenseDefense += -10;
            atr.IndependenceCooperation += 0;
            atr.DarknessLight += 15;
            atr.TemperanceEnthusiasm += 0;
            atr.NatureTechnology += 0;
            atr.PastFuture += 10;
        } else if (favocolor == "black") {
            atr.BrainMuscle += -10;
            atr.AgilityWeight += 0;
            atr.OffenseDefense += -15;
            atr.IndependenceCooperation += -5;
            atr.DarknessLight += 20;
            atr.TemperanceEnthusiasm += -5;
            atr.NatureTechnology += 0;
            atr.PastFuture += 0;
        } else if (favocolor == "white") {
            atr.BrainMuscle += 10;
            atr.AgilityWeight += 0;
            atr.OffenseDefense += 5;
            atr.IndependenceCooperation += 10;
            atr.DarknessLight += 5;
            atr.TemperanceEnthusiasm += 5;
            atr.NatureTechnology += 0;
            atr.PastFuture += 0;
        } else {
            throw std::runtime_error("Invalid favorite color");
        }

    } catch (const std::exception& e) {
        cerr << "Error caught: " << e.what() << endl;
    }
}
