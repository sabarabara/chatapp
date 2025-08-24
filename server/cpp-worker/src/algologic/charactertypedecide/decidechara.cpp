#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <utility>
#include <limits>
#include <algorithm>
#include <climits>
#include "decidechara.h"
using ll = long long;
using namespace std;

string decidecharacter(Attributes &atr){
    vector<pair<string, short>> parseatr {
        {"BrainMuscle", atr.BrainMuscle},
        {"AgilityWeight", atr.AgilityWeight},
        {"OffenseDefense", atr.OffenseDefense},
        {"IndependenceCooperation", atr.IndependenceCooperation},
        {"DarknessLight", atr.DarknessLight},
        {"TemperanceEnthusiasm", atr.TemperanceEnthusiasm},
        {"NatureTechnology", atr.NatureTechnology},
        {"PastFuture", atr.PastFuture}
    };

    // キャラクターのベクトル定義
    map<string, vector<int>> charVectors = {
        {"Swordsman",   {10, 5, 15, 5, 0, 5, 0, -5}},
        {"Mage",        {15, 0, 0, 0, -5, 0, 10, 10}},
        {"Knight",      {-10, -15, 10, 5, 0, 5, 0, 5}},
        {"Ninja",       {-5, 10, 10, -5, -10, 10, -5, 0}},
        {"Thief",       {5, 10, 5, -5, 0, 5, -10, 0}},
        {"Archer",      {0, 5, 0, 10, -5, 0, 15, 0}},
        {"Clown",       {10, -5, -10, 0, 15, 0, 0, 10}},
        {"Berserker",   {-10, 15, 20, 0, -5, 15, 0, 0}},
        {"Bard",        {10, 0, 5, 10, 5, 5, 0, 0}},
        {"Alchemist",   {0, 0, 0, 0, 0, 0, 0, 0}}, // 適当に埋める
        {"Priest",      {5, 0, -5, 5, 10, -5, 5, 0}} // 適当に埋める
    };


    string bestChar;
    ll minDistSq = LLONG_MAX;

    for (auto &[name, vec] : charVectors) {
        ll distSq = 0;
        for (int i = 0; i < 8; i++) {
            ll diff = parseatr[i].second - vec[i];
            distSq += diff * diff;
        }
        if (distSq < minDistSq) {
            minDistSq = distSq;
            bestChar = name;
        }
    }

    cout << "Best character: " << bestChar << endl;
    return bestChar;
}
