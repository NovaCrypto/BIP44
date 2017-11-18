/*
 *  BIP44
 *  Copyright (C) 2017 Alan Evans, NovaCrypto
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Original source: https://github.com/NovaCrypto/BIP44
 *  You can contact the authors via github issues.
 */

package io.github.novacrypto.bip44;

public final class Purpose {
    private final int purpose;
    private final String toString;

    Purpose(final int purpose) {
        this.purpose = purpose;
        toString = String.format("m/%d'", purpose);
    }

    public static Purpose purpose(final int purpose) {
        return new Purpose(purpose);
    }

    public int getValue() {
        return purpose;
    }

    @Override
    public String toString() {
        return toString;
    }

    public CoinType coinType(final int coinType) {
        return new CoinType(this, coinType);
    }
}