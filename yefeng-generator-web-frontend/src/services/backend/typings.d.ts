declare namespace API {
  type BaseResponseBoolean = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseGeneratorVO = {
    code?: number;
    data?: GeneratorVO;
    message?: string;
  };

  type BaseResponseLoginUserVO = {
    code?: number;
    data?: LoginUserVO;
    message?: string;
  };

  type BaseResponseLong = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageGenerator = {
    code?: number;
    data?: PageGenerator;
    message?: string;
  };

  type BaseResponsePageGeneratorVO = {
    code?: number;
    data?: PageGeneratorVO;
    message?: string;
  };

  type BaseResponsePageUser = {
    code?: number;
    data?: PageUser;
    message?: string;
  };

  type BaseResponsePageUserVO = {
    code?: number;
    data?: PageUserVO;
    message?: string;
  };

  type BaseResponseString = {
    code?: number;
    data?: string;
    message?: string;
  };

  type BaseResponseUser = {
    code?: number;
    data?: User;
    message?: string;
  };

  type BaseResponseUserVO = {
    code?: number;
    data?: UserVO;
    message?: string;
  };

  type DeleteRequest = {
    id?: number;
  };

  type FileConfig = {
    sourceRootPath?: string;
    inputRootPath?: string;
    outputRootPath?: string;
    type?: string;
    files?: FileInfo[];
  };

  type FileInfo = {
    inputPath?: string;
    outputPath?: string;
    type?: string;
    generateType?: string;
    condition?: string;
    groupKey?: string;
    groupName?: string;
  };

  type Generator = {
    id?: number;
    name?: string;
    description?: string;
    basePackage?: string;
    version?: string;
    author?: string;
    tags?: string;
    picture?: string;
    fileConfig?: string;
    modelConfig?: string;
    distPath?: string;
    status?: number;
    userId?: number;
    createTime?: string;
    updateTime?: string;
    isDelete?: number;
  };

  type GeneratorAddRequest = {
    name?: string;
    description?: string;
    basePackage?: string;
    version?: string;
    author?: string;
    tags?: string[];
    picture?: string;
    fileConfig?: FileConfig;
    modelConfig?: ModelConfig;
    distPath?: string;
    status?: number;
  };

  type GeneratorEditRequest = {
    id?: number;
    name?: string;
    description?: string;
    basePackage?: string;
    version?: string;
    author?: string;
    tags?: string[];
    picture?: string;
    fileConfig?: FileConfig;
    modelConfig?: ModelConfig;
    distPath?: string;
  };

  type GeneratorQueryRequest = {
    current?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    id?: number;
    notId?: number;
    searchText?: string;
    tags?: string[];
    orTags?: string[];
    userId?: number;
    name?: string;
    description?: string;
    basePackage?: string;
    version?: string;
    author?: string;
    distPath?: string;
    status?: number;
  };

  type GeneratorUpdateRequest = {
    id?: number;
    name?: string;
    description?: string;
    basePackage?: string;
    version?: string;
    author?: string;
    tags?: string[];
    picture?: string;
    fileConfig?: FileConfig;
    modelConfig?: ModelConfig;
    distPath?: string;
    status?: number;
  };

  type GeneratorVO = {
    user?: UserVO;
    id?: number;
    name?: string;
    description?: string;
    basePackage?: string;
    version?: string;
    author?: string;
    tags?: string[];
    picture?: string;
    fileConfig?: FileConfig;
    modelConfig?: ModelConfig;
    distPath?: string;
    status?: number;
    userId?: number;
    createTime?: string;
    updateTime?: string;
  };

  type getGeneratorVOByIdParams = {
    id: number;
  };

  type getUserByIdParams = {
    id: number;
  };

  type getUserVOByIdParams = {
    id: number;
  };

  type LoginUserVO = {
    id?: number;
    userName?: string;
    userAvatar?: string;
    userProfile?: string;
    userRole?: string;
    createTime?: string;
    updateTime?: string;
  };

  type ModelConfig = {
    models?: ModelInfo[];
  };

  type ModelInfo = {
    fieldName?: string;
    type?: string;
    description?: string;
    defaultValue?: Record<string, any>;
    abbr?: string;
    groupKey?: string;
    groupName?: string;
    condition?: string;
    allArgsStr?: string;
  };

  type OrderItem = {
    column?: string;
    asc?: boolean;
  };

  type PageGenerator = {
    records?: Generator[];
    total?: number;
    size?: number;
    current?: number;
    orders?: OrderItem[];
    optimizeCountSql?: boolean;
    searchCount?: boolean;
    optimizeJoinOfCountSql?: boolean;
    countId?: string;
    maxLimit?: number;
    pages?: number;
  };

  type PageGeneratorVO = {
    records?: GeneratorVO[];
    total?: number;
    size?: number;
    current?: number;
    orders?: OrderItem[];
    optimizeCountSql?: boolean;
    searchCount?: boolean;
    optimizeJoinOfCountSql?: boolean;
    countId?: string;
    maxLimit?: number;
    pages?: number;
  };

  type PageUser = {
    records?: User[];
    total?: number;
    size?: number;
    current?: number;
    orders?: OrderItem[];
    optimizeCountSql?: boolean;
    searchCount?: boolean;
    optimizeJoinOfCountSql?: boolean;
    countId?: string;
    maxLimit?: number;
    pages?: number;
  };

  type PageUserVO = {
    records?: UserVO[];
    total?: number;
    size?: number;
    current?: number;
    orders?: OrderItem[];
    optimizeCountSql?: boolean;
    searchCount?: boolean;
    optimizeJoinOfCountSql?: boolean;
    countId?: string;
    maxLimit?: number;
    pages?: number;
  };

  type uploadFileParams = {
    uploadFileRequest: UploadFileRequest;
  };

  type UploadFileRequest = {
    biz?: string;
  };

  type User = {
    id?: number;
    userAccount?: string;
    userPassword?: string;
    userName?: string;
    userAvatar?: string;
    userProfile?: string;
    userRole?: string;
    createTime?: string;
    updateTime?: string;
    isDelete?: number;
  };

  type UserAddRequest = {
    userName?: string;
    userAccount?: string;
    userAvatar?: string;
    userRole?: string;
  };

  type UserLoginRequest = {
    userAccount?: string;
    userPassword?: string;
  };

  type UserQueryRequest = {
    current?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    id?: number;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserRegisterRequest = {
    userAccount?: string;
    userPassword?: string;
    checkPassword?: string;
  };

  type UserUpdateMyRequest = {
    userName?: string;
    userAvatar?: string;
    userProfile?: string;
  };

  type UserUpdateRequest = {
    id?: number;
    userName?: string;
    userAvatar?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserVO = {
    id?: number;
    userName?: string;
    userAvatar?: string;
    userProfile?: string;
    userRole?: string;
    createTime?: string;
  };
}
