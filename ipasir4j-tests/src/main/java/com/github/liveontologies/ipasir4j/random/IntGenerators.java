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
 * A collection of static methods for creating random number generators
 * 
 * @author Yevgeny Kazakov
 */
public class IntGenerators {

	/**
	 * Generates random numbers according to the specified array of parameters.
	 * This array is of the form {@code [n1,c1,n2,c2,n3,c3,...,nk,ck,n{k+1}]}
	 * where {@code n1, n2,...,n{k+1}]} is a strictly increasing sequence of
	 * numbers and each {@code ci} specifies the number of values to be
	 * generated between {@code ni} (inclusive) and {@code n{i+1}} (exclusive).
	 * 
	 * @param random
	 *            the random generator used for generating the values
	 * @param parameters
	 *            the array of integer parameters that specifies how many
	 *            numbers within specific ranges should be generated
	 * @return an {@link IntGenerator} for the specified parameters
	 */
	public static IntGenerator getGenerator(Random random, int[] parameters) {
		IntGenerator[] generators = new IntGenerator[(parameters.length - 1)
				/ 2];
		for (int i = 0; i + 2 < parameters.length; i += 2) {
			generators[i / 2] = new UniformIntGenerator(random, parameters[i],
					parameters[i + 2], parameters[i + 1]);
		}
		return new CombinedIntGenerator(random, generators);
	}

}
