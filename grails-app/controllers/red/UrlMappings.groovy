package red

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/dbconsole"(redirect:'/h2-console')
        "/db"(redirect: '/h2-console')
    }
}
