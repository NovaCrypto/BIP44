[![Download](https://api.bintray.com/packages/novacrypto/BIP/BIP44/images/download.svg)](https://bintray.com/novacrypto/BIP/BIP44/_latestVersion) [![Build Status](https://travis-ci.org/NovaCrypto/BIP44.svg?branch=master)](https://travis-ci.org/NovaCrypto/BIP44) [![codecov](https://codecov.io/gh/NovaCrypto/BIP44/branch/master/graph/badge.svg)](https://codecov.io/gh/NovaCrypto/BIP44)

# Install

Repository:

```
repositories {
    maven {
        url 'https://dl.bintray.com/novacrypto/BIP/'
    }
}
```

Add dependency:

```
dependencies {
    compile 'io.github.novacrypto:BIP44:0.0.1'
}

```

# Usage

## Fluent construction

```
AddressIndex addressIndex = purpose(44)
                                .coinType(2)
                                .account(1)
                                .external()
                                .address(5);
```

## To string

```
String path = purpose(44)
                  .coinType(2)
                  .account(1)
                  .external()
                  .address(5)
                  .toString(); //"m/44'/2'/1'/0/5"
```

# Deriving

Using [NovaCrypto/BIP32](https://github.com/NovaCrypto/BIP32) keys.

# WIP

!!! Note that the dependency [BIP32](https://github.com/NovaCrypto/BIP32) is a work in progress and you shouldn't use this just yet for any main net transactions. !!!

## From root

```
PrivateKey addressKey = rootPrivateKey
                  .derive(addressIndex, AddressIndex.DERIVATION);
```

## Account from root

```
Account account = purpose(44)
                  .coinType(2)
                  .account(1);
PrivateKey addressKey = rootPrivateKey
                  .derive(account, Account.DERIVATION);
```

## From account private

```
PrivateKey addressKey = accountPrivateKey
                  .derive(addressIndex, AddressIndex.DERIVATION_FROM_ACCOUNT);
```

## From account public

```
PublicKey addressKey = accountPublicKey
                  .derive(addressIndex, AddressIndex.DERIVATION_FROM_ACCOUNT);
```
