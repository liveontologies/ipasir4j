package com.github.liveontologies.ipasir4j.testing;

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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.github.liveontologies.ipasir4j.ClauseReader;
import com.github.liveontologies.ipasir4j.IpasirSolver;
import com.github.liveontologies.ipasir4j.LearningListener;
import com.github.liveontologies.ipasir4j.SolverTerminatedException;
import com.github.liveontologies.ipasir4j.random.IntGenerators;
import com.github.liveontologies.ipasir4j.random.RandomClauseGenerator;
import com.github.liveontologies.ipasir4j.random.RandomClauseGeneratorBySizes;

/**
 * Generating random satisfiabile clauses and testing the results of the solver
 * 
 * @author Yevgeny Kazakov
 */
public abstract class IpasirSolverRandomSatTest {

	IpasirSolver solver;
	long seed;
	Random random;

	protected abstract IpasirSolver createSolver();

	@Before
	public void before() {
		solver = createSolver();
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}

	@Test
	public void random2Sat1() {
		random3Sat(generateModels(2, 10), 100, 3);
	}

	@Test
	public void random2Sat2() {
		random3Sat(generateModels(2, 10), 1000, 5);
	}

	@Test
	public void random3Sat1() {
		random3Sat(generateModels(2, 10), 100, 3);
	}

	@Test
	public void random3Sat2() {
		random3Sat(generateModels(2, 10), 1000, 5);
	}

	@Test
	public void random3Sat3() {
		random3Sat(generateModels(2, 100), 1000, 5);
	}

	@Test
	public void randomSat01() {
		randomSat(generateModels(1, 100),
				new int[] { 1, 10, 2, 20, 3, 500, 4, 300, 5, 200, 6 }, 5);
	}

	@Test
	public void randomSat02() {
		randomSat(generateModels(1, 100),
				new int[] { 1, 10, 2, 20, 3, 1000, 4 }, 3);
	}

	@Test
	public void randomSat03() {
		randomSat(generateModels(1, 300),
				new int[] { 1, 10, 2, 20, 3, 500, 4, 300, 5, 200, 6 }, 5);
	}

	@Test
	public void randomSat04() {
		randomSat(generateModels(1, 300),
				new int[] { 1, 10, 2, 20, 3, 1000, 4 }, 3);
	}

	@Test
	public void randomSat05() {
		randomSat(generateModels(2, 100),
				new int[] { 2, 20, 3, 500, 4, 300, 5, 200, 6 }, 5);
	}

	@Test
	public void randomSat06() {
		randomSat(generateModels(2, 100), new int[] { 2, 20, 3, 1000, 4 }, 3);
	}

	@Test
	public void randomSat07() {
		randomSat(generateModels(2, 300),
				new int[] { 2, 20, 3, 500, 4, 300, 5, 200, 6 }, 5);
	}

	@Test
	public void randomSat08() {
		randomSat(generateModels(2, 300), new int[] { 2, 20, 3, 1000, 4 }, 3);
	}

	@Test
	public void randomSat09() {
		randomSat(generateModels(3, 300), new int[] { 3, 1000, 4 }, 3);
	}

	@Test
	public void randomSat10() {
		randomSat(generateModels(3, 300),
				new int[] { 3, 500, 4, 300, 5, 200, 6 }, 5);
	}

	@Test
	public void randomSat11() {
		randomSat(generateModels(3, 300), new int[] { 3, 1000, 4 }, 3);
	}

	void random2Sat(boolean[][] models, int modelSize, int clauseCount,
			int maxLearnedClauseLength) {
		randomSat(models, new int[] { 2, clauseCount, 3 },
				maxLearnedClauseLength);
	}

	/**
	 * Generates a random set of clauses with 3 literals that is satisfiable in
	 * all randomly generated models
	 * 
	 * @param modelCount
	 * @param modelSize
	 * @param clauseCount
	 * @param maxLearnedClauseLength
	 */
	void random3Sat(boolean[][] models, int clauseCount,
			int maxLearnedClauseLength) {
		randomSat(models, new int[] { 3, clauseCount, 4 },
				maxLearnedClauseLength);
	}

	/**
	 * Generates a random set of clauses that is satisfiable in all randomly
	 * generated models
	 * 
	 * @param modelCount
	 *            the number of models to be generated
	 * @param modelSize
	 *            the number of propositional assignments in each generated
	 *            model
	 * @param clauseSizes
	 *            specifies how many clauses of each length should be generated
	 * @param maxLearnedClauseLength
	 *            the bound on the length of the learned clauses reported
	 * 
	 * @see IntGenerators#getGenerator(Random, int[])
	 */
	void randomSat(boolean[][] models, int[] clauseSizes,
			int maxLearnedClauseLength) {
		try {
			randomSat(
					models, new RandomClauseGeneratorBySizes(random,
							models[0].length, IntGenerators
									.getGenerator(random, clauseSizes)),
					maxLearnedClauseLength);
		} catch (Throwable e) {
			throw new RuntimeException("Seed: " + seed, e);
		}

	}

	/**
	 * Generates a random model (represented by an array of truth values) of a
	 * given size
	 * 
	 * @param modelSize
	 * @return the generated model
	 */
	boolean[] generateModel(int modelSize) {
		final boolean[] isTrue = new boolean[modelSize];
		for (int i = 0; i < isTrue.length; i++) {
			isTrue[i] = random.nextBoolean();
		}
		return isTrue;
	}

	/**
	 * Generates a specified number random models (represented by a
	 * 2-dimensional array of truth values) of a given size
	 * 
	 * @param modelCount
	 * @param modelSize
	 * @return
	 */
	boolean[][] generateModels(int modelCount, int modelSize) {
		final boolean[][] models = new boolean[modelCount][];
		for (int m = 0; m < models.length; m++) {
			models[m] = generateModel(modelSize);
		}
		return models;
	}

	/**
	 * Computes the value of a literal in a model
	 * 
	 * @param isTrue
	 *            the array representing truth values of propositional variables
	 *            (the truth value of variable n is saved at position n-1).
	 * @param l
	 *            a non-zero integer interpreted as a literal to be evaluated
	 * @return the input literal if it is true in the model and its negation if
	 *         it is false in the model
	 */
	static int getValue(boolean[] isTrue, int l) {
		if (l == 0) {
			throw new RuntimeException("A literal cannot be 0!");
		}
		boolean literalIsTrue = l > 0 ? isTrue[l - 1] : !isTrue[-1 - l];
		return literalIsTrue ? l : -l;
	}

	/**
	 * Runs a solver on a randomly generated set of clauses that are true in the
	 * given models
	 * 
	 * @param modelCount
	 *            the number of models to generate
	 * @param modelSize
	 *            the number of propositional variables assigned in models
	 * @param clauseGenerator
	 *            specifies how random clauses are generated
	 * @param maxLearnedClauseLength
	 *            the bound on the size of the learned clauses to be reported
	 */
	void randomSat(boolean[][] models, RandomClauseGenerator clauseGenerator,
			int maxLearnedClauseLength) {
		final int[] learnedClause = new int[maxLearnedClauseLength];
		// checking that all learned clauses are true in the model
		LearningListener checkingListener = new LearningListener() {
			@Override
			public void clauseLearned(ClauseReader reader) {
				// reading the learned clause
				int l = 0;
				for (int i = 0; i < learnedClause.length; i++) {
					l = learnedClause[i] = reader.getNextLiteralOrZero();
					if (l == 0) {
						break;
					}
				}
				if (l != 0) {
					assertTrue(
							"Learned clause must have at most "
									+ maxLearnedClauseLength + " literals",
							reader.getNextLiteralOrZero() == 0);
				}
				// check if at least one literal is true in each model
				modelLoop: for (int m = 0; m < models.length; m++) {
					for (int i = 0; i < learnedClause.length; i++) {
						l = learnedClause[i];
						if (l == 0) {
							fail("Learned clause is false: " + Arrays
									.toString(Arrays.copyOf(learnedClause, i)));
						}
						if (getValue(models[m], l) == l) {
							continue modelLoop; // the literal is true
						}
					}
					fail("Learned clause is false:"
							+ Arrays.toString(learnedClause));
				}
			}
		};
		solver.setLearningListener(maxLearnedClauseLength, checkingListener);
		// generating clauses
		int[] clause;
		int[] trueLiteralPositions = new int[models.length];
		while ((clause = clauseGenerator.getNextClause()) != null) {
			// System.out.println(Arrays.toString(clause));
			// chose a distinct random literal position for each model
			fillWithDistinctRandomValues(trueLiteralPositions, clause.length);
			for (int m = 0; m < models.length; m++) {
				int l = random.nextInt(models[m].length) + 1;
				l = getValue(models[m], l);
				assert (getValue(models[m], l) == l);
				clause[trueLiteralPositions[m]] = l;
			}
			// adding the clause to the solver
			for (int j = 0; j < clause.length; j++) {
				solver.add(clause[j]);
			}
			solver.add(0);
			// checking satisfiability
			try {
				assertTrue("The clauses must be satisfiable",
						solver.isSatisfiable());
			} catch (SolverTerminatedException e) {
				fail("Unexpected solver termination");
			}
		}

	}

	/**
	 * Fills the given array of values with random distinct values between 0
	 * (inclusive) and the given bound (exclusive). The values are written in
	 * the increasing order.
	 * 
	 * @param values
	 * @param maxVal
	 * @return
	 */
	int[] fillWithDistinctRandomValues(int[] values, int maxVal) {
		for (int i = 0; i < values.length; i++) {
			int next = random.nextInt(maxVal - i);
			for (int j = 0; j < i; j++) {
				if (next < values[j]) {
					int tmp = values[j];
					values[j] = next;
					next = tmp;
				} else if (next == values[j]) {
					next++;
				}
			}
			values[i] = next;
		}
		return values;
	}

}
