package org.example.textflowproject.domain.home.service;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.domain.home.dto.HomeResponse;
import org.example.textflowproject.domain.home.repository.HomeQueryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

  private final HomeQueryRepository homeQueryRepository;

  public HomeResponse getHome() {

    return new HomeResponse(
        homeQueryRepository.getBanners(),
        homeQueryRepository.getRecommendations(),
        homeQueryRepository.getNewByGenre(),
        homeQueryRepository.getPopular(),
        homeQueryRepository.getEvents()
    );
  }
}
