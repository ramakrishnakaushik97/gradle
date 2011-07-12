/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.plugins.cpp

class SourceSetConfigurationSpec extends CppProjectSpec {

    def setup() {
        applyPlugin()
    }

    def "configure source sets"() {
        when:
        cpp {
            sourceSets {
                ss1 {
                    cpp {
                        srcDirs "d1", "d2"
                    }
                }
                ss2 {
                    cpp {
                        srcDirs "d3"
                    }
                }
            }
        }

        then:
        def ss1 = cpp.sourceSets.ss1
        def ss2 = cpp.sourceSets.ss2

        // cpp dir automatically added by convention
        ss1.cpp.srcDirs*.name == ["cpp", "d1", "d2"]
        ss2.cpp.srcDirs*.name == ["cpp", "d3"]
    }
}