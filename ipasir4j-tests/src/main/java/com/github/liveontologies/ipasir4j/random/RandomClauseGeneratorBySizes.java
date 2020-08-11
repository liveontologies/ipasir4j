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
 * Generate random clauses according to the array of clause size parameters.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class RandomClauseGeneratorBySizes implements RandomClauseGenerator {

	private final Random random_;

	private final int modelSize_;

	private final IntGenerator clauseSizeGenerator_;

	/**
	 * Construct a new clause generator with the provided parameters
	 * 
	 * @param random
	 *            the generator for clause sizes and literals
	 * @param modelSize
	 *            the number of propositional variables that can be used in
	 *            clauses
	 * @param clauseSizeGenerator
	 *            a random generator for clause sizes
	 */
	public RandomClauseGeneratorBySizes(Random random, int modelSize,
			IntGenerator clauseSizeGenerator) {
		this.random_ = random;
		this.modelSize_ = modelSize;
		this.clauseSizeGenerator_ = clauseSizeGenerator;
	}

	@Override
	public int[] getNextClause() {
		if (clauseSizeGenerator_.getRemainingCount() == 0) {
			// all clauses have been generated
			return null;
		}
		int s = clauseSizeGenerator_.getNextValue();
		// generating a random clause of size s
		int[] clause = new int[s];
		for (int i = 0; i < s; i++) {
			int l = random_.nextInt(modelSize_) + 1;
			l *= random_.nextBoolean() ? 1 : -1;
			clause[i] = l;
		}
		return clause;
	}

}
