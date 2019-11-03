package com.addressbook.domain.builder;

public interface IBuilder<T> {
    //return a new object instance
    T get();
}
