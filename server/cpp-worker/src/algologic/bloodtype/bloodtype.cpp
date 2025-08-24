#include <bits/stdc++.h>
#include "bloodtype.h"
using ll = long long;
using namespace std;

void bloodtype(const UserData &user, Attributes &atr)
{

  try
  {
    if (user.blood_type == "A")
    {
      atr.BrainMuscle += 10;
      atr.AgilityWeight += 0;
      atr.OffenseDefense += -5;
      atr.IndependenceCooperation += 5;
      atr.DarknessLight += 10;
      atr.TemperanceEnthusiasm += -5;
      atr.NatureTechnology += 5;
      atr.PastFuture += 0;
    }
    else if (user.blood_type == "B")
    {
      atr.BrainMuscle += -5;
      atr.AgilityWeight += 10;
      atr.OffenseDefense += 10;
      atr.IndependenceCooperation += -5;
      atr.DarknessLight += -10;
      atr.TemperanceEnthusiasm += 10;
      atr.NatureTechnology += -5;
      atr.PastFuture += 0;
    }
    else if (user.blood_type == "AB")
    {
      atr.BrainMuscle += 15;
      atr.AgilityWeight += 0;
      atr.OffenseDefense += 0;
      atr.IndependenceCooperation += 0;
      atr.DarknessLight += -5;
      atr.TemperanceEnthusiasm += 0;
      atr.NatureTechnology += 10;
      atr.PastFuture += 10;
    }
    else if (user.blood_type == "O")
    {
      atr.BrainMuscle += 5;
      atr.AgilityWeight += 5;
      atr.OffenseDefense += 15;
      atr.IndependenceCooperation += 10;
      atr.DarknessLight += 0;
      atr.TemperanceEnthusiasm += 5;
      atr.NatureTechnology += 0;
      atr.PastFuture += -5;
    }
    else
    {
      throw runtime_error("Invalid blood type: must be A, B, AB, or O");
    }
  }
  catch (const exception &e)
  {
    cerr << "Error caught: " << e.what() << endl;
  }
}