JHM_OPTIONS := '{:mode :sample :fork 1 :format :table :only [:params :score :score-error] :output-time-unit :us}'

repl-rc1:
	clj -M:repl:2.0.0-RC1 -m nrepl.cmdline --middleware [cider.nrepl/cider-middleware]

repl-rc2:
	clj -M:repl:2.0.0-RC2 -m nrepl.cmdline --middleware [cider.nrepl/cider-middleware]

bench-rc1:
	mkdir -p classes && clj -X:jmh:2.0.0-RC1 ${JHM_OPTIONS}

bench-rc2:
	mkdir -p classes && clj -X:jmh:2.0.0-RC2 ${JHM_OPTIONS}
