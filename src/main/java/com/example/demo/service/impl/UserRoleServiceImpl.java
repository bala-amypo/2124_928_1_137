@Override
public UserRole assignRole(UserRole userRole) {

    Long userId = userRole.getUser().getId();
    Long roleId = userRole.getRole().getId();

    UserAccount user = userRepository.findById(userId)
            .orElseThrow(() ->
                new ResourceNotFoundException("User not found"));

    Role role = roleRepository.findById(roleId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Role not found"));

    UserRole ur = new UserRole();
    ur.setUser(user);
    ur.setRole(role);

    UserRole saved = userRoleRepository.save(ur);

    // 🔥 IMPORTANT FIX (same as RolePermission)
    return userRoleRepository.findById(saved.getId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Mapping not found"));
}
