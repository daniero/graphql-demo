import React, { useState } from 'react';
import { Breweries } from "./components/Breweries";
import layout from './Root.module.css';
import { Brewery } from "./components/Brewery";
import { Beer } from "./components/Beer";

export const Root = () => {
  const [selectedBrewery, setSelectedBrewery] = useState(null);
  const [selectedBeer, setSelectedBeer] = useState(null);

  return (
    <div className={layout.container}>
      <Breweries
        selectedBrewery={selectedBrewery}
        setSelectedBrewery={(brewery) => {
          setSelectedBeer(null);
          setSelectedBrewery(brewery);
        }}
      />
      {selectedBrewery && (
        <Brewery
          key={selectedBrewery.id}
          brewery={selectedBrewery}
          selectedBeer={selectedBeer}
          setSelectedBeer={setSelectedBeer}
        />
      )}
      {selectedBeer && (
        <Beer
          key={selectedBeer.id}
          beer={selectedBeer}
        />
      )}
    </div>
  );
};

