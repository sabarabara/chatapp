// json_utils.h
#pragma once
#include "data.h"
#include "../json.hpp"

inline void from_json(const nlohmann::json& j, UserData& u) {
    j.at("blood_type").get_to(u.blood_type);
    j.at("height").get_to(u.height);
    j.at("birthday").get_to(u.birthday);
    j.at("favorite_weather").get_to(u.favorite_weather);
    j.at("favorite_color").get_to(u.favorite_color);
    j.at("dominant_hand").get_to(u.dominant_hand);
}
