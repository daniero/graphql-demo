* Apollo Cache

    Frontend cacher data og oppdaterer visningen etter mutations.

* Directives

    Slippe å drive med dynamisk oppbygging av queries selv:
    
    - https://graphql.org/learn/queries/#directives

* Pagination

  Lage en query som bare gir en håndfull øl om gangen, istedet for alle på en gang.
  nøkkelord: `connection`, `edge`, `cursor`, etc
  
  - https://graphql.org/learn/pagination/

* "Partial results"

    Returnere både data og error på en gang, hvis deler av hentingen gikk galt.
    _Some data is better than no data_:
    
    - https://www.graphql-java.com/blog/deep-dive-data-fetcher-results/,
    - https://blog.apollographql.com/full-stack-error-handling-with-graphql-apollo-5c12da407210

* Batching / DataLoader

  Mer effektiv uthenting av data; Kalle de underliggende service-metodene så få ganger som mulig.

