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

import java.util.Random;

/**
 * An {@link IntGenerator} that is composed of several {@link IntGenerator}s.
 * Each value is generated by one of the included {@link IntGenerator}. Thus,
 * the number of values that it generates is the sum of the number of values for
 * each included {@link IntGenerator}. The generated values come in a random
 * order.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class CombinedIntGenerator extends ZeroGenerator {

	private final Random random_;
	private final IntGenerator[] distributionGenerators_;

	public CombinedIntGenerator(Random random,
			IntGenerator[] distributionGenerators) {
		super(countValuesToGenerate(distributionGenerators));
		this.random_ = random;
		this.distributionGenerators_ = distributionGenerators;
	}

	@Override
	public int getNextValue() {
		super.getNextValue();
		// assuming all remaining values are sorted by their corresponding
		// generators, get a random value position
		int valPos = random_.nextInt(getRemainingCount() + 1);
		int genPos = 0; // find the generator to be used for this position
		for (int c = 0; (c += distributionGenerators_[genPos]
				.getRemainingCount()) <= valPos; genPos++) {
		}
		return distributionGenerators_[genPos].getNextValue();
	}

	private static int countValuesToGenerate(
			IntGenerator[] distributionGenerators) {
		int result = 0;
		for (int i = 0; i < distributionGenerators.length; i++) {
			result += distributionGenerators[i].getRemainingCount();
		}
		return result;
	}

}