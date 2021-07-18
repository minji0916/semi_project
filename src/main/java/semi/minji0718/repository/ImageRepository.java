package semi.minji0718.repository;

import semi.minji0718.domain.MyImage;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {//extends JpaRepository<MyImage, Long> { // entity name, pk type

   //MyImage findById(String imageName);
    MyImage saveImage(MyImage image);
    Optional<MyImage> findByNo(Long no);
    Optional<MyImage> findByLibraryNo(Long library_no);
}
