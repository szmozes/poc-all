package hu.szmozes.authengine.controller

import hu.szmozes.authengine.model.GetPermissionsRequest
import hu.szmozes.authengine.service.PermissionService
import hu.szmozes.authengine.service.SampleDataService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val sampleDataService: SampleDataService,
    private val permissionService: PermissionService,
) {

    @PostMapping("/load-sample-data")
    fun loadSampleData(): ResponseEntity<Void> {
        sampleDataService.loadSampleData()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/get-permissions")
    fun getPermissions(@RequestBody getPermissionsRequest: GetPermissionsRequest): ResponseEntity<List<String>> {
        val permissions = permissionService.getPermissions(getPermissionsRequest)
        return ResponseEntity.ok(permissions)
    }
}
