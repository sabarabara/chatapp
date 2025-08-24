// data.h
#pragma once
#include <string>


struct UserData {
    std::string blood_type;
    short height;
    std::string birthday;
    std::string favorite_weather;
    std::string favorite_color;
    std::string dominant_hand;
};

struct Attributes {
    short BrainMuscle = 50;
    short AgilityWeight = 50;
    short OffenseDefense = 50;
    short IndependenceCooperation = 50;
    short DarknessLight = 50;
    short TemperanceEnthusiasm = 50;
    short NatureTechnology = 50;
    short PastFuture = 50;
};