import React from 'react';
import { useQuery } from "@apollo/react-hooks";
import { gql } from "apollo-boost";
import classnames from 'classnames';
import style from './Brewery.module.css'
import { ItemList } from "./ItemList";
import layout from '../Root.module.css';


const query = gql`
    query getBrewery($breweryId: ID!) {
        brewery(id: $breweryId) {
            id
            name
            city
            state
            beers {
                id
                name
            }
        }
    }
`;


export const Brewery = ({
                          brewery,
                          selectedBeer,
                          setSelectedBeer
                        }) => {

  const { loading, error, data } = useQuery(query, { variables: { breweryId: brewery.id } });

  return (
    <div className={classnames(
      loading && style.loading,
      layout.column
    )}>
      <h1 className={layout.fixed}>
        {brewery.name}
      </h1>

      {error && (
        <pre>{JSON.stringify(error, undefined, 2)}</pre>
      )}

      {data && data.brewery && (
        <>
          {/*<h2>{data.brewery.city}, {data.brewery.state}</h2>*/}
          <ItemList
            className={layout.stretch}
            items={data.brewery.beers}
            selected={selectedBeer}
            setSelected={setSelectedBeer}
          />
        </>
      )}
    </div>
  );
};