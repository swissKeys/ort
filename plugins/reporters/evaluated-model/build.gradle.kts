/*
 * Copyright (C) 2023 The ORT Project Authors (see <https://github.com/oss-review-toolkit/ort/blob/main/NOTICE>)
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

plugins {
    // Apply precompiled plugins.
    id("ort-library-conventions")
}

dependencies {
    api(project(":model"))
    api(project(":reporter"))
    api(project(":utils:spdx-utils"))

    api(libs.jacksonAnnotations)
    api(libs.jacksonDatabind)

    implementation(project(":utils:ort-utils"))

    implementation(libs.jacksonCore)
    implementation(libs.jacksonDataformatYaml)

    funTestImplementation(testFixtures(project(":reporter")))
}
