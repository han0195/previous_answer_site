package kbuni_question.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
        return null;
    }
}
