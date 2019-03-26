package router

import (
	logApi "golang_simle_REST_service/features"

	"github.com/julienschmidt/httprouter"
)

// ApplicationRoutes is to handle all the API Requests
func ApplicationRoutes(router *httprouter.Router) {
	logApi.Routes(router)
}
