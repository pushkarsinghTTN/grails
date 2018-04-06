package linksharing

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")
        "/"(controller: "login", action: "index")
//        "/topic/show"(view: 'topic/show')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
