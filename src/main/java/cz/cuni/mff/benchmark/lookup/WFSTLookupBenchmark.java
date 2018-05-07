package cz.cuni.mff.benchmark.lookup;

import org.apache.lucene.search.suggest.fst.WFSTCompletionLookup;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.IOException;

public class WFSTLookupBenchmark {

    @State(Scope.Benchmark)
    public static class MyState {

        private WFSTCompletionLookup lookup;

        @Setup(Level.Trial)
        public void setup() throws IOException {
            lookup = new WFSTCompletionLookup(LookupUtils.getTempDir(), "suffix");

            LookupUtils.build(lookup);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void oneLetterPrefixLookup(MyState state) throws IOException {
        LookupUtils.oneLetterPrefixLookup(state.lookup);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void twoLetterPrefixLookup(MyState state) throws IOException {
        LookupUtils.twoLetterPrefixLookup(state.lookup);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void nonPrefixLookup(MyState state) throws IOException {
        LookupUtils.nonPrefixLookup(state.lookup);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void buildTime() throws IOException {
        WFSTCompletionLookup lookup = new WFSTCompletionLookup(LookupUtils.getTempDir(), "suffix");
        LookupUtils.build(lookup);
    }

}