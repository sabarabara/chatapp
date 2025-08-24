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
    svr.Post("/characterType", [](const httplib::Request& req, httplib::Response& res) {
    try {
        json j = json::parse(req.body);
        cout<< "🐞Received JSON: " << j.dump(4) << endl;

        UserData user;
        from_json(j, user);
        cout << "Parsed UserData: " << user.blood_type
             << ", " << user.height
             << ", " << user.birthday << endl;

        string charactertype = processUserData(user);  // ← ここが結果

        cout << "OK: " << charactertype << endl;

        // ✅ Java 側にそのまま文字列を返す
        res.set_content(charactertype, "text/plain");
    } catch (const std::exception& e) {
        res.status = 400;
        res.set_content(string("Invalid JSON: ") + e.what(), "text/plain");
    }
});

    cout << "C++ HTTP server running on http://localhost:8080\n";
    svr.listen("0.0.0.0", 8080);
}
