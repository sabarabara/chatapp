#include <bits/stdc++.h>
#include "algorithm.h"
using ll = long long;
using namespace std;


//logic include
#include "algologic/bloodtype/bloodtype.h"
#include "algologic/birhtday/birthday.h"
#include "algologic/dominanthand/dominanthand.h"
#include "algologic/favoritecolor/favoritecolor.h"
#include "algologic/favoriteweather/favoriteweather.h"
#include "algologic/height/height.h"





string processUserData(const UserData& user) {
    cout << "User blood type: " << user.blood_type << "\n";
    cout << "Birthday: " << user.birthday << "\n";

  
  //ここからがlogic

  Attributes atr;//->userの特徴データ初期化

  birthday(user,atr);
  bloodtype(user,atr);
  dominanthand(user,atr);
  favoritecolor(user,atr);
  favoriteweather(user,atr);
  height(user,atr);

  cout<<atr.AgilityWeight<<endl;
  cout<<atr.BrainMuscle<<endl;
  cout<<atr.DarknessLight<<endl;
  cout<<atr.IndependenceCooperation<<endl;
  cout<<atr.NatureTechnology<<endl;
  cout<<atr.OffenseDefense<<endl;
  cout<<atr.PastFuture<<endl;
  cout<<atr.TemperanceEnthusiasm<<endl;



  //ここからユークリッド距離の和の最少を導出
  return "OK";//いったんここまで
}
