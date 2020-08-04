Java library for IPASIR

IPASIR is reverse for "Reentrant Incremental Sat solver API". This is a C
interface that can be used to work with incremental propositional satisfiability
(SAT) solvers. This interface allows to add new clauses and assumptions to SAT
solvers and (incrementally) check satisfiability of the resulting formulas. This
interface was defined for the incremental library track of the SAT competition
(see http://www.satcompetition.org). Several highly efficient SAT solvers, such
as MiniSat, PicoSAT, Glucose, CaDiCal, CryptoMiniSat and others support this 
interface.

The goal of this project is to be able to use IPASIR solvers from Java. Towards
this goal, this project provides a Java interface IpasirSolver that follows
closely the C interface, a translation from the native interface to the
Java interface by means of a JNA glue code, and a helper maven project that can
be used to package native SAT solvers as Maven modules.  

Currently this project contains the following modules:

ipasir4j-build-helper		a maven module for packaging SAT solvers
ipasir4j-jna				accessing native IPASIR libraries using JNA
ipasir4j-model				Java interfaces analogous to IPASIR
ipasir4j-tests				unit tests for the Java IPASIR interfaces 

These modules can be used in many different ways:

1. To access a native IPASIR library from Java, add ipasir4j-jna as a dependency
   of your project.

2. To provide an IPASIR interface to your SAT solver, add ipasir4j-model as a
   dependency (and, optionally, ipasir4j-tests as test dependency) of your
   project.

3. To simplify packaging of a native IPASIR library as a maven module, select 
   ipasir4j-build-helper as a parent of your maven project.


All sources of this project are available under the conditions of the Apache
Software License, Version 2.0 (see LICENSE.txt).