package org.iass.openfeign.apple

import org.iass.openfeign.dto.ApplePublicKeys
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "apple-public-key-client", url = "https://appleid.apple.com/auth")
interface AppleApiClient {
	@GetMapping(value = ["/keys"], consumes = ["application/json;charset=UTF-8"])
	fun getApplePublicKeys(): ApplePublicKeys
}
