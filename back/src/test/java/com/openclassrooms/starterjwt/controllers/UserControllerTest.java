package com.openclassrooms.starterjwt.controllers;

public class UserControllerTesst {
    @Autowired
    MockMvc mockMvc;

    @Mock
    private UserController userController;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;
    private UserDto userDto;
    private List<UserDto> userDtos;
    private List<User> users;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userController = new UserController(userService, userMapper);
        Long id = 1L;
        String lastName = "toto";
        String firstName = "tata";
        user = new User();
        user.setId(id);
        user.setLastName(lastName);
        user.setFirstName(firstName);

        userDto = userMapper.toDto(user);
        userDtos = Collections.singletonList(userDto);
        users = Collections.singletonList(user);
    }

    @Test
    void findById() {
        when(userService.findById(1L)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);
        ResponseEntity<?> responseEntity = userController.findById("1");
        assertEquals(userDto, responseEntity.getBody());
    }

    @Test
    void findById_should_return_not_found() {
        when(userService.findById(1L)).thenReturn(null);
        ResponseEntity<?> responseEntity = userController.findById("1");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete() {

        Long id = 1L;

        when(userService.findById(1L)).thenReturn(user);
        UserDetails userDetails = mock(UserDetails.class);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));
        ResponseEntity<?> responseEntity = userController.save(id.toString());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


    }

    @Test
    void delete_should_return_not_found() {
        Long id = 1L;
        when(userService.findById(1L)).thenReturn(null);
        ResponseEntity<?> responseEntity = userController.save(id.toString());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete_should_return_unauthorized() {
        Long id = 1L;
        when(userService.findById(1L)).thenReturn(user);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("toto.com");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));
        ResponseEntity<?> responseEntity = userController.save(id.toString());
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    @WithMockUser
    public void shouldFindById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/1")).andExpect(status().isOk());
    }
}
