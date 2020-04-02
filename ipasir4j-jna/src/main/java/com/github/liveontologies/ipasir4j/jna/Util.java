package com.github.liveontologies.ipasir4j.jna;

/*-
 * #%L
 * JNA interfaces for the IPASIR C header
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

import com.sun.jna.Pointer;

/**
 * A collection of utility methods
 * 
 * @author Yevgeny Kazakov
 *
 */
public class Util {

	/**
	 * Reads a 0-terminating integer array from the pointer
	 * 
	 * @param p
	 * @return the resulting array
	 */
	public static int[] getIntArray(Pointer p) {
		int offset = 0;
		int size = 0;
		while (p.getInt(offset) != 0) {
			offset += 4; // TODO: 4 bytes for int - platform independent?
			size++;
		}
		return p.getIntArray(0, size);
	}

}
