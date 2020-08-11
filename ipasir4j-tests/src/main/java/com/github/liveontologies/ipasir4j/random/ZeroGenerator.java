package com.github.liveontologies.ipasir4j.random;

/*-
 * #%L
 * Tests for Java IPASIR interfaces
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2020 Live Ontologies Project
 * %%
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
 * #L%
 */

import java.util.NoSuchElementException;

/**
 * A dummy {@link IntGenerator} that generates a specified number of 0 values.
 * Mainly useful as a prototype of other {@link IntGenerator}s.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ZeroGenerator implements IntGenerator {

	/**
	 * the number of values that are remained to be generated
	 */
	private int remainingValueCount_;

	public ZeroGenerator(int valuesToGenerate) {
		this.remainingValueCount_ = valuesToGenerate;
	}

	@Override
	public int getRemainingCount() {
		return remainingValueCount_;
	}

	@Override
	public int getNextValue() {
		if (remainingValueCount_-- == 0) {
			throw new NoSuchElementException("All values have been generated");
		}
		return 0;
	}

}
