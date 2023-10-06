# hiccup-benchmark

## Bar
<img width="888" alt="image" src="https://github.com/Panthevm/hiccup-benchmark/assets/43318093/fd40252d-e97e-4791-a8bd-a136a42d4b16">

## Diff flamegraph
![image](https://github.com/Panthevm/hiccup-benchmark/assets/43318093/d46f3d49-debd-4483-957f-bedb7ba3574a)

> 2.0.0-RC1 - red, 
> 2.0.0-RC2 - blue

## Benchmarks

### 2.0.0-RC1

```
:score         :score-error  :params                                         
-------------  ------------  ------------------------------------------------
2.187   us/op  0.018         {:variants []}                                  
3.684   us/op  0.018         {:variants [:tag-id]}                           
5.207   us/op  0.017         {:variants [:tag-class]}                        
6.970   us/op  0.042         {:variants [:tag-id :tag-class]}                
15.443  us/op  0.065         {:variants [:tag-id :tag-class :attrs-id]}      
16.075  us/op  0.073         {:variants [:tag-id :tag-class :attrs-id :attrs-class]}
24.812  us/op  0.067         {:variants [:tag-id :tag-class :attrs-id :attrs-class :attrs-styles]}
25.461  us/op  0.068         {:variants [:all {:children 1}]}
29.663  us/op  0.077         {:variants [:all {:children 10}]}
54.968  us/op  0.100         {:variants [:all {:children 1 :depth 1}]}
83.912  us/op  0.160         {:variants [:all {:children 1 :depth 2}]}
```

<details>
  <summary>GC Profile</summary>
  
```
:benchmark                          :score             :score-error  :score-confidence
---------------------------------   -----------------  ------------  --------------------
  gc.alloc.rate                     2664.204   MB/sec  45.236        2618.968   2709.440
  gc.alloc.rate.norm                88037.890  B/op    0.041         88037.849  88037.932
  gc.churn.G1_Eden_Space            2666.879   MB/sec  66.363        2600.515   2733.242
  gc.churn.G1_Eden_Space.norm       88125.730  B/op    896.733       87228.996  89022.463
  gc.churn.G1_Survivor_Space        0.025      MB/sec  0.023         0.002      0.048
  gc.churn.G1_Survivor_Space.norm   0.824      B/op    0.759         0.064      1.583
  gc.count                          417.000    counts                417.000    417.000
  gc.time                           449.000    ms                    449.000    449.000
```

</details>

### 2.0.0-RC2

```
:score         :score-error  :params                                         
-------------  ------------  ------------------------------------------------
0.450   us/op  0.015         {:variants []}                                  
1.291   us/op  0.020         {:variants [:tag-id]}                           
1.719   us/op  0.019         {:variants [:tag-class]}                        
2.139   us/op  0.024         {:variants [:tag-id :tag-class]}                
7.299   us/op  0.075         {:variants [:tag-id :tag-class :attrs-id]}      
7.314   us/op  0.044         {:variants [:tag-id :tag-class :attrs-id :attrs-class]}
12.264  us/op  0.061         {:variants [:tag-id :tag-class :attrs-id :attrs-class :attrs-styles]}
13.220  us/op  0.069         {:variants [:all {:children 1}]}
15.298  us/op  0.082         {:variants [:all {:children 10}]}
31.010  us/op  0.109         {:variants [:all {:children 1 :depth 1}]}
47.677  us/op  0.175         {:variants [:all {:children 1 :depth 2}]}
```

<details>
  <summary>GC Profile</summary>
  
```
:benchmark                          :score             :score-error  :score-confidence
---------------------------------   -----------------  ------------  --------------------
  gc.alloc.rate                     2664.204   MB/sec  45.236        2618.968   2709.440
  gc.alloc.rate.norm                88037.890  B/op    0.041         88037.849  88037.932
  gc.churn.G1_Eden_Space            2666.879   MB/sec  66.363        2600.515   2733.242
  gc.churn.G1_Eden_Space.norm       88125.730  B/op    896.733       87228.996  89022.463
  gc.churn.G1_Survivor_Space        0.025      MB/sec  0.023         0.002      0.048
  gc.churn.G1_Survivor_Space.norm   0.824      B/op    0.759         0.064      1.583
  gc.count                          417.000    counts                417.000    417.000
  gc.time                           449.000    ms                    449.000    449.000
```

</details>

## Dev

- make bench-rc1
- make bench-rc2
- make repl-rc1
- make repl-rc2
