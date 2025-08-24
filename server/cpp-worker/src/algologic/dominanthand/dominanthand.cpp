#include <bits/stdc++.h>
#include "dominanthand.h"
using ll = long long;
using namespace std;

void dominanthand(const UserData &user, Attributes &atr)
{

  try
  {

    string hand = user.dominant_hand;

    if (hand == "right")
    {
      // 利き手右
      atr.BrainMuscle += 0;
      atr.AgilityWeight += 0;
      atr.OffenseDefense += 0;
      atr.IndependenceCooperation += 0;
      atr.DarknessLight += 0;
      atr.TemperanceEnthusiasm += 0;
      atr.NatureTechnology += 0;
      atr.PastFuture += 0;
    }
    else if (hand == "left")
    {
      // 利き手左
      atr.BrainMuscle += 5;
      atr.AgilityWeight += 5;
      atr.OffenseDefense += -5;
      atr.IndependenceCooperation += 5;
      atr.DarknessLight += 5;
      atr.TemperanceEnthusiasm += 0;
      atr.NatureTechnology += 0;
      atr.PastFuture += 0;
    }
    else if (hand == "ambidextrous")
    {
      // 両利き
      atr.BrainMuscle += 10;
      atr.AgilityWeight += 10;
      atr.OffenseDefense += 0;
      atr.IndependenceCooperation += 10;
      atr.DarknessLight += 0;
      atr.TemperanceEnthusiasm += 10;
      atr.NatureTechnology += 5;
      atr.PastFuture += 5;
    }

    else
    {
      throw runtime_error("Invalid dominant type: must be right,left,ambidextrous");
    }
  }
  catch (const exception &e)
  {
    cerr << "Error caught: " << e.what() << endl;
  }
}