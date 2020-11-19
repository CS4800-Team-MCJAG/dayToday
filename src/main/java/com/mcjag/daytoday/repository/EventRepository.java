package com.mcjag.daytoday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcjag.daytoday.tables.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}