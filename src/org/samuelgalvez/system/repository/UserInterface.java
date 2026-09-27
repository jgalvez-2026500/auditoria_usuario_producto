/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.samuelgalvez.system.repository;

 import org.samuelgalvez.system.model.User;
public interface UserInterface {

    void create(User user);

    User findByEmail(String email);
}
