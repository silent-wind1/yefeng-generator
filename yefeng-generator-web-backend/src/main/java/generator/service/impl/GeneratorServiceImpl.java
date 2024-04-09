package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Generator;
import generator.mapper.GeneratorMapper;
import generator.service.GeneratorService;
import org.springframework.stereotype.Service;

/**
* @author 29515
* @description 针对表【generator(代码生成器)】的数据库操作Service实现
* @createDate 2024-04-09 18:57:23
*/
@Service
public class GeneratorServiceImpl extends ServiceImpl<GeneratorMapper, Generator>
    implements GeneratorService{

}




