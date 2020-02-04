import React from 'react';
import { useQuery } from '@apollo/react-hooks';
import { gql } from 'apollo-boost';
import { ItemList } from "./ItemList";
import layout from '../Root.module.css';

const query = gql`
    query getAllBreweries {
        breweries: allBreweries {
            id
            name
        }
    }
`;

export const Breweries = ({
                            selectedBrewery,
                            setSelectedBrewery
                          }) => {
  return (
    <div className={layout.column}>
      <h1 className={layout.fixed}>Breweries</h1>
      <BreweryList
        selectedBrewery={selectedBrewery}
        setSelectedBrewery={setSelectedBrewery}
      />
    </div>
  );
};

const BreweryList = ({
                       selectedBrewery,
                       setSelectedBrewery
                     }) => {

  const { loading, error, data: { breweries } = {} } = useQuery(query);

  if (loading) {
    return (
      <div>Loading ...</div>
    );
  }

  if (error) {
    return (
      <div>{JSON.stringify(error, undefined, 2)}</div>
    );
  }

  return (
    <ItemList
      className={layout.stretch}
      items={breweries}
      selected={selectedBrewery}
      setSelected={setSelectedBrewery}
    />
  );
};