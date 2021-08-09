/*
 * Copyright (C) 2021 Bosch.IO GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

import org.ossreviewtoolkit.clients.fossid.FossIdRestService
import org.ossreviewtoolkit.clients.fossid.FossIdServiceWithVersion
import org.ossreviewtoolkit.clients.fossid.VersionedFossIdService21dot2

private const val PROJECT_CODE = "semver4j"
private const val SCAN_CODE_21_2 = "${PROJECT_CODE}_20201203_090342_21.2"

/**
 * This client test tests the calls that have been changed for FossID 21.2.
 */
class FossId21dot2Test : StringSpec({
    val wiremock = WireMockServer(
        WireMockConfiguration.options()
            .dynamicPort()
            .usingFilesUnderDirectory("src/test/assets/21.2")
    )
    lateinit var service: FossIdServiceWithVersion

    beforeSpec {
        wiremock.start()
        WireMock.configureFor(wiremock.port())
        service = FossIdServiceWithVersion.instance(FossIdRestService.create("http://localhost:${wiremock.port()}"))
    }

    afterSpec {
        wiremock.stop()
    }

    beforeTest {
        wiremock.resetAll()
    }

    "Version can be parsed of login page (21.2)" {
        service.version shouldBe "2021.2.2"
        service.shouldBeInstanceOf<VersionedFossIdService21dot2>()
    }
})
