/*
 * Copyright (C) 2017 Apple Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY APPLE INC. ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL APPLE INC. OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
"use strict";


class CardDeck
{
    constructor()
    {
        this.newDeck();
    }

    newDeck()
    {
        // Make a shallow copy of a new deck
        this._cards = CardDeck._newDeck.slice(0);
    }

    shuffle()
    {
        this.newDeck();

        for (let index = 52; index !== 0;) {
            // Select a random card
            let randomIndex = Math.floor(Math.random() * index);
            index--;

            // Swap the current card with the random card
            let tempCard = this._cards[index];
            this._cards[index] = this._cards[randomIndex];
            this._cards[randomIndex] = tempCard;
        }
    }

    dealOneCard()
    {
        return this._cards.shift();
    }

    static cardRank(card)
    {
        // This returns a numeric value for a card.
        // Ace is highest.

        let rankOfCard = card.codePointAt(0) & 0xf;
        if (rankOfCard == 0x1) // Make Aces higher than Kings
            rankOfCard = 0xf;

        return rankOfCard;
    }

    static cardName(card)
    {      
        if (typeof(card) == "string")
            card = card.codePointAt(0);
        return this._rankNames[card & 0xf];
    }
}

CardDeck._rankNames = [
    "", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "", "Queen", "King"
];

CardDeck._newDeck = [
    // Spades
    "\u{1f0a1}", "\u{1f0a2}",  "\u{1f0a3}",  "\u{1f0a4}",  "\u{1f0a5}",
    "\u{1f0a6}", "\u{1f0a7}",  "\u{1f0a8}",  "\u{1f0a9}",  "\u{1f0aa}",
    "\u{1f0ab}", "\u{1f0ad}",  "\u{1f0ae}",
    // Hearts
    "\u{1f0b1}", "\u{1f0b2}",  "\u{1f0b3}",  "\u{1f0b4}",  "\u{1f0b5}",
    "\u{1f0b6}", "\u{1f0b7}",  "\u{1f0b8}",  "\u{1f0b9}",  "\u{1f0ba}",
    "\u{1f0bb}", "\u{1f0bd}",  "\u{1f0be}",
    // Clubs
    "\u{1f0d1}", "\u{1f0d2}",  "\u{1f0d3}",  "\u{1f0d4}",  "\u{1f0d5}",
    "\u{1f0d6}", "\u{1f0d7}",  "\u{1f0d8}",  "\u{1f0d9}",  "\u{1f0da}",
    "\u{1f0db}", "\u{1f0dd}",  "\u{1f0de}",
    // Diamonds
    "\u{1f0c1}", "\u{1f0c2}",  "\u{1f0c3}",  "\u{1f0c4}",  "\u{1f0c5}",
    "\u{1f0c6}", "\u{1f0c7}",  "\u{1f0c8}",  "\u{1f0c9}",  "\u{1f0ca}",
    "\u{1f0cb}", "\u{1f0cd}",  "\u{1f0ce}"
];

class Hand
{
    constructor()
    {
        this.clear();
    }

    clear()
    {
        this._cards = [];
        this._rank = 0;
    }

    takeCard(card)
    {
        this._cards.push(card);
    }

    score()
    {
        // Sort highest rank to lowest
        this._cards.sort((a, b) => {
            return CardDeck.cardRank(b) - CardDeck.cardRank(a);
        });

        let handString = this._cards.join("");

        let flushResult = handString.match(Hand.FlushRegExp);
        let straightResult = handString.match(Hand.StraightRegExp);
        let ofAKindResult = handString.match(Hand.OfAKindRegExp);

        if (flushResult) {
            if (straightResult) {
                if (straightResult[1])
                    this._rank = Hand.RoyalFlush;
                else
                    this._rank = Hand.StraightFlush
            } else
                this._rank = Hand.Flush;

            this._rank |= CardDeck.cardRank(this._cards[0]) << 16 | CardDeck.cardRank(this._cards[1]) << 12;
        } else if (straightResult)
            this._rank = Hand.Straight | CardDeck.cardRank(this._cards[0]) << 16 | CardDeck.cardRank(this._cards[1]) << 12;
        else if (ofAKindResult) {
            // When comparing lengths, a matched unicode character has a length of 2.
            // Therefore expected lengths are doubled, e.g a pair will have a match length of 4.
            if (ofAKindResult[0].length == 8)
                this._rank = Hand.FourOfAKind | CardDeck.cardRank(this._cards[0]);
            else {
                // Found pair or three of a kind.  Check for two pair or full house.
                let firstOfAKind = ofAKindResult[0];
                let remainingCardsIndex = handString.indexOf(firstOfAKind) + firstOfAKind.length;
                let secondOfAKindResult;
                if (remainingCardsIndex <= 6
                    && (secondOfAKindResult = handString.slice(remainingCardsIndex).match(Hand.OfAKindRegExp))) {
                    if ((firstOfAKind.length == 6 && secondOfAKindResult[0].length == 4)
                        || (firstOfAKind.length == 4 && secondOfAKindResult[0].length == 6)) {
                        let threeOfAKindCardRank;
                        let twoOfAKindCardRank;
                        if (firstOfAKind.length == 6) {
                            threeOfAKindCardRank = CardDeck.cardRank(firstOfAKind.slice(0,2));
                            twoOfAKindCardRank = CardDeck.cardRank(secondOfAKindResult[0].slice(0,2));
                        } else {
                            threeOfAKindCardRank = CardDeck.cardRank(secondOfAKindResult[0].slice(0,2));
                            twoOfAKindCardRank = CardDeck.cardRank(firstOfAKind.slice(0,2));
                        }
                        this._rank = Hand.FullHouse | threeOfAKindCardRank << 16 | threeOfAKindCardRank < 12 | threeOfAKindCardRank << 8 | twoOfAKindCardRank << 4 | twoOfAKindCardRank;
                    } else if (firstOfAKind.length == 4 && secondOfAKindResult[0].length == 4) {
                        let firstPairCardRank = CardDeck.cardRank(firstOfAKind.slice(0,2));
                        let SecondPairCardRank = CardDeck.cardRank(secondOfAKindResult[0].slice(0,2));
                        let otherCardRank;
                        // Due to sorting, the other card is at index 0, 4 or 8
                        if (firstOfAKind.codePointAt(0) == handString.codePointAt(0)) {
                            if (secondOfAKindResult[0].codePointAt(0) == handString.codePointAt(4))
                                otherCardRank = CardDeck.cardRank(handString.slice(8,10));
                            else
                                otherCardRank = CardDeck.cardRank(handString.slice(4,6));
                        } else
                            otherCardRank = CardDeck.cardRank(handString.slice(0,2));

                        this._rank = Hand.TwoPair | firstPairCardRank << 16 | firstPairCardRank << 12 | SecondPairCardRank << 8 | SecondPairCardRank << 4 | otherCardRank;
                    }
                } else {
                    let ofAKindCardRank = CardDeck.cardRank(firstOfAKind.slice(0,2));
                    let otherCardsRank = 0;
                    for (let card of this._cards) {
                        let cardRank = CardDeck.cardRank(card);
                        if (cardRank != ofAKindCardRank)
                            otherCardsRank = (otherCardsRank << 4) | cardRank;
                    }

                    if (firstOfAKind.length == 6)
                        this._rank = Hand.ThreeOfAKind | ofAKindCardRank << 16 | ofAKindCardRank << 12 | ofAKindCardRank << 8 | otherCardsRank;
                    else
                        this._rank = Hand.Pair | ofAKindCardRank << 16 | ofAKindCardRank << 12 | otherCardsRank;
                }
            }
        } else {
            this._rank = 0;
            for (let card of this._cards) {
                let cardRank = CardDeck.cardRank(card);
                this._rank = (this._rank << 4) | cardRank;
            }
        }
    }

    get rank()
    {
        return this._rank;
    }

    toString()
    {
        return this._cards.join("");
    }
}

Hand.FlushRegExp = new RegExp("([\u{1f0a1}-\u{1f0ae}]{5})|([\u{1f0b1}-\u{1f0be}]{5})|([\u{1f0c1}-\u{1f0ce}]{5})|([\u{1f0d1}-\u{1f0de}]{5})", "u");

Hand.StraightRegExp = new RegExp("([\u{1f0a1}\u{1f0b1}\u{1f0d1}\u{1f0c1}][\u{1f0ae}\u{1f0be}\u{1f0de}\u{1f0ce}][\u{1f0ad}\u{1f0bd}\u{1f0dd}\u{1f0cd}][\u{1f0ab}\u{1f0bb}\u{1f0db}\u{1f0cb}][\u{1f0aa}\u{1f0ba}\u{1f0da}\u{1f0ca}])|[\u{1f0ae}\u{1f0be}\u{1f0de}\u{1f0ce}][\u{1f0ad}\u{1f0bd}\u{1f0dd}\u{1f0cd}][\u{1f0ab}\u{1f0bb}\u{1f0db}\u{1f0cb}][\u{1f0aa}\u{1f0ba}\u{1f0da}\u{1f0ca}][\u{1f0a9}\u{1f0b9}\u{1f0d9}\u{1f0c9}]|[\u{1f0ad}\u{1f0bd}\u{1f0dd}\u{1f0cd}][\u{1f0ab}\u{1f0bb}\u{1f0db}\u{1f0cb}][\u{1f0aa}\u{1f0ba}\u{1f0da}\u{1f0ca}][\u{1f0a9}\u{1f0b9}\u{1f0d9}\u{1f0c9}][\u{1f0a8}\u{1f0b8}\u{1f0d8}\u{1f0c8}]|[\u{1f0ab}\u{1f0bb}\u{1f0db}\u{1f0cb}][\u{1f0aa}\u{1f0ba}\u{1f0da}\u{1f0ca}][\u{1f0a9}\u{1f0b9}\u{1f0d9}\u{1f0c9}][\u{1f0a8}\u{1f0b8}\u{1f0d8}\u{1f0c8}][\u{1f0a7}\u{1f0b7}\u{1f0d7}\u{1f0c7}]|[\u{1f0aa}\u{1f0ba}\u{1f0da}\u{1f0ca}][\u{1f0a9}\u{1f0b9}\u{1f0d9}\u{1f0c9}][\u{1f0a8}\u{1f0b8}\u{1f0d8}\u{1f0c8}][\u{1f0a7}\u{1f0b7}\u{1f0d7}\u{1f0c7}][\u{1f0a6}\u{1f0b6}\u{1f0d6}\u{1f0c6}]|[\u{1f0a9}\u{1f0b9}\u{1f0d9}\u{1f0c9}][\u{1f0a8}\u{1f0b8}\u{1f0d8}\u{1f0c8}][\u{1f0a7}\u{1f0b7}\u{1f0d7}\u{1f0c7}][\u{1f0a6}\u{1f0b6}\u{1f0d6}\u{1f0c6}][\u{1f0a5}\u{1f0b5}\u{1f0d5}\u{1f0c5}]|[\u{1f0a8}\u{1f0b8}\u{1f0d8}\u{1f0c8}][\u{1f0a7}\u{1f0b7}\u{1f0d7}\u{1f0c7}][\u{1f0a6}\u{1f0b6}\u{1f0d6}\u{1f0c6}][\u{1f0a5}\u{1f0b5}\u{1f0d5}\u{1f0c5}][\u{1f0a4}\u{1f0b4}\u{1f0d4}\u{1f0c4}]|[\u{1f0a7}\u{1f0b7}\u{1f0d7}\u{1f0c7}][\u{1f0a6}\u{1f0b6}\u{1f0d6}\u{1f0c6}][\u{1f0a5}\u{1f0b5}\u{1f0d5}\u{1f0c5}][\u{1f0a4}\u{1f0b4}\u{1f0d4}\u{1f0c4}][\u{1f0a3}\u{1f0b3}\u{1f0d3}\u{1f0c3}]|[\u{1f0a6}\u{1f0b6}\u{1f0d6}\u{1f0c6}][\u{1f0a5}\u{1f0b5}\u{1f0d5}\u{1f0c5}][\u{1f0a4}\u{1f0b4}\u{1f0d4}\u{1f0c4}][\u{1f0a3}\u{1f0b3}\u{1f0d3}\u{1f0c3}][\u{1f0a2}\u{1f0b2}\u{1f0d2}\u{1f0c2}]|[\u{1f0a1}\u{1f0b1}\u{1f0d1}\u{1f0c1}][\u{1f0a5}\u{1f0b5}\u{1f0d5}\u{1f0c5}][\u{1f0a4}\u{1f0b4}\u{1f0d4}\u{1f0c4}][\u{1f0a3}\u{1f0b3}\u{1f0d3}\u{1f0c3}][\u{1f0a2}\u{1f0b2}\u{1f0d2}\u{1f0c2}]", "u");

Hand.OfAKindRegExp = new RegExp("(?:[\u{1f0a1}\u{1f0b1}\u{1f0d1}\u{1f0c1}]{2,4})|(?:[\u{1f0ae}\u{1f0be}\u{1f0de}\u{1f0ce}]{2,4})|(?:[\u{1f0ad}\u{1f0bd}\u{1f0dd}\u{1f0cd}]{2,4})|(?:[\u{1f0ab}\u{1f0bb}\u{1f0db}\u{1f0cb}]{2,4})|(?:[\u{1f0aa}\u{1f0ba}\u{1f0da}\u{1f0ca}]{2,4})|(?:[\u{1f0a9}\u{1f0b9}\u{1f0d9}\u{1f0c9}]{2,4})|(?:[\u{1f0a8}\u{1f0b8}\u{1f0d8}\u{1f0c8}]{2,4})|(?:[\u{1f0a7}\u{1f0b7}\u{1f0d7}\u{1f0c7}]{2,4})|(?:[\u{1f0a6}\u{1f0b6}\u{1f0d6}\u{1f0c6}]{2,4})|(?:[\u{1f0a5}\u{1f0b5}\u{1f0d5}\u{1f0c5}]{2,4})|(?:[\u{1f0a4}\u{1f0b4}\u{1f0d4}\u{1f0c4}]{2,4})|(?:[\u{1f0a3}\u{1f0b3}\u{1f0d3}\u{1f0c3}]{2,4})|(?:[\u{1f0a2}\u{1f0b2}\u{1f0d2}\u{1f0c2}]{2,4})", "u");

Hand.RoyalFlush = 0x900000;
Hand.StraightFlush = 0x800000;
Hand.FourOfAKind = 0x700000;
Hand.FullHouse = 0x600000;
Hand.Flush = 0x500000;
Hand.Straight = 0x400000;
Hand.ThreeOfAKind = 0x300000;
Hand.TwoPair = 0x200000;
Hand.Pair = 0x100000;

class Player extends Hand
{
    constructor(name)
    {
        super();
        this._name = name;
        this._wins = 0;
        this._handTypeCounts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    }

    scoreHand()
    {
        this.score();
        let handType = this.rank >> 20;
        this._handTypeCounts[handType]++;
    }

    wonHand()
    {
        this._wins++
    }

    get name()
    {
        return this._name;
    }

    get hand()
    {
        return super.toString();
    }

    get wins()
    {
        return this._wins;
    }

    get handTypeCounts()
    {
        return this._handTypeCounts;
    }
}

function playHands(players)
{
    let cardDeck = new CardDeck();
    let handsPlayed = 0;
    let highestRank = 0;

    do {
        cardDeck.shuffle();

        for (let player of players)
            player.clear();

        for (let i = 0; i < 5; i++) {
            for (let player of players)
                player.takeCard(cardDeck.dealOneCard());
        }

        for (let player of players)
            player.scoreHand();

        handsPlayed++;

        highestRank = 0;

        for (let player of players) {
            if (player.rank > highestRank)
                highestRank = player.rank;
        }

        for (let player of players) {
            // We count ties as wins for each player.
            if (player.rank == highestRank)
                player.wonHand();
        }
    } while (handsPlayed < 2000);
}





class Benchmark {
    constructor() {
        this.iterations = 1;
        this.benchmarkName = "poker.js";
    }
    runIteration() {
        let p1 = new Player("Player 1");
        let p2 = new Player("Player 2");
        playHands([p1,p2])
    }
    runBenchmark() {
        let iterations = this.iterations;
        console.log("Running " + this.benchmarkName + " for " + iterations + " iterations.");
        for (let i = 0; i < iterations; ++i)
            this.runIteration();

        console.time(this.benchmarkName)
        for (let i = 0; i < iterations; ++i)
            this.runIteration();
        console.timeEnd(this.benchmarkName)
        console.log(this.benchmarkName + " finished running.");
    }
}
let bench = new Benchmark();
bench.runBenchmark();