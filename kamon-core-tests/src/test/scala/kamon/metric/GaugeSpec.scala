/* =========================================================================================
 * Copyright © 2013-2017 the kamon project <http://kamon.io/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * =========================================================================================
 */

package kamon.metric

import kamon.Kamon
import kamon.testkit.InstrumentInspection
import org.scalatest.{Matchers, WordSpec}

class GaugeSpec extends WordSpec with Matchers with InstrumentInspection.Syntax {

  "a Gauge" should {
    "have a starting value of zero" in {
      val gauge = Kamon.gauge("default-value").withoutTags()
      gauge.value shouldBe 0D
    }

    "retain the last value recorded on it" in {
      val gauge = Kamon.gauge("retain-value").withoutTags().update(42D)
      gauge.value shouldBe 42D
      gauge.value shouldBe 42D

      gauge.update(17D)
      gauge.value shouldBe 17D
      gauge.value shouldBe 17D
    }
  }
}
