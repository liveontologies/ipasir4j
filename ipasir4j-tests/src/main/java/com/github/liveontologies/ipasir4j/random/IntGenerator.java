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

/**
 * A generator that can be used to generate a fixed number of random integers
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface IntGenerator {

	/**
	 * @return the number of values remained to generate
	 */
	int getRemainingCount();

	/**
	 * @return the next value to generate
	 */
	int getNextValue();

}
