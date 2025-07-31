#include "httplib.h"
#include <iostream>

int main() {
    httplib::Server svr;

    svr.Get("/status", [](const httplib::Request&, httplib::Response& res) {
        res.set_content("C++ worker OK", "text/plain");
    });

    std::cout << "C++ HTTP server running on http://localhost:8083\n";
    svr.listen("0.0.0.0", 8083);
}
