#include "httplib.h"
#include <iostream>
#include "json.hpp"
#include "src/data.h"
#include "src/json_utils.h"
#include "src/algorithm.h"

using namespace std;
using json = nlohmann::json;

int main() {
    httplib::Server svr;

    // ヘルスチェック
    svr.Get("/status", [](const httplib::Request&, httplib::Response& res) {
        res.set_content("C++ worker OK", "text/plain");
    });

    // POSTでJSONデータを受け取る
    svr.Post("/submit", [](const httplib::Request& req, httplib::Response& res) {
        try {
            json j = json::parse(req.body);

            UserData user;
            from_json(j, user);

            string charactertype = processUserData(user);  // algo.cpp で処理

            res.set_content("User data received and processed.", "text/plain");
        } catch (const std::exception& e) {
            res.status = 400;
            res.set_content(string("Invalid JSON: ") + e.what(), "text/plain");
        }
    });

    cout << "C++ HTTP server running on http://localhost:8083\n";
    svr.listen("0.0.0.0", 8083);
}
