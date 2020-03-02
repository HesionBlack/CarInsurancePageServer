<template>
    <div>
        <div>
            <div style="display: flex;justify-content: space-between">
                <div>
                    <el-input placeholder="请输入险种名称进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                              clearable
                              @clear="initAccepts"
                              style="width: 350px;margin-right: 10px" v-model="keyword"
                              @keydown.enter.native="initAccepts" :disabled="showAdvanceSearchView"></el-input>
                    <el-button icon="el-icon-search" type="primary" @click="initAccepts"
                               :disabled="showAdvanceSearchView">
                        搜索
                    </el-button>
                    <!--                    <el-button type="primary" @click="showAdvanceSearchView = !showAdvanceSearchView">-->
                    <!--                        <i :class="showAdvanceSearchView?'fa fa-angle-double-up':'fa fa-angle-double-down'"-->
                    <!--                           aria-hidden="true"></i>-->
                    <!--                        高级搜索-->
                    <!--                    </el-button>-->
                </div>
            </div>
            <transition name="slide-fade">
                <div v-show="showAdvanceSearchView"
                     style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
                    <el-row>
                        <el-col :span="5">
                            车主姓名:
                            <el-input v-model="searchValue.carmaster" placeholder="车主姓名" size="mini"
                                      style="width: 130px;"/>

                        </el-col>
                        <el-col :span="4">
                            车辆品牌:
                            <el-input v-model="searchValue.brand" placeholder="车辆品牌" size="mini" style="width: 130px;"/>
                        </el-col>
                        <el-col :span="4">
                            车辆型号:
                            <el-input v-model="searchValue.model" placeholder="车辆型号" size="mini" style="width: 130px;"/>
                        </el-col>
                    </el-row>
                    <el-row style="margin-top: 10px">
                        <el-col :span="5" :offset="4">
                            <el-button size="mini">取消</el-button>
                            <el-button size="mini" icon="el-icon-search" type="primary"
                                       @click="initAccepts('advanced')">
                                搜索
                            </el-button>
                        </el-col>
                    </el-row>
                </div>
            </transition>
        </div>
        <div style="margin-top: 10px">
            <el-table
                    :data="accepts"
                    stripe
                    border
                    v-loading="loading"
                    element-loading-text="正在加载..."
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)"
                    style="width: 100%">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="id"
                        align="left"
                        fixed
                        label="承保单号"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="carbrand"
                        label="品牌型号"
                        width="85">
                </el-table-column>
                <el-table-column
                        prop="carmaster"
                        width="110"
                        fixed="right"
                        label="车主姓名">
                </el-table-column>
                <el-table-column
                        prop="name"
                        width="120"
                        label="险种姓名">
                </el-table-column>
                <el-table-column
                        prop="finalprice"
                        width="120"
                        label="应付价格">
                </el-table-column>
                <el-table-column
                        prop="accept"
                        width="120"
                        label="申请状态">
                    <template slot-scope="scope">
                        <span v-if="scope.row.accept===0">未处理</span>
                        <span v-if="scope.row.accept===1" style="color: #b2e281">同意</span>
                        <span v-if="scope.row.accept===2" style="color: #e30007">拒绝</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        width="160"
                        label="投保时间">
                </el-table-column>
                <el-table-column
                        prop="createBy"
                        width="140"
                        label="投保操作人">
                </el-table-column>
                <el-table-column
                        prop="updateTime"
                        width="200"
                        fixed="right"
                        label="最新操作时间">
                </el-table-column>
                <el-table-column
                        prop="updateBy"
                        width="200"
                        fixed="right"
                        label="操作人">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        width="200"
                        label="操作">
                    <template slot-scope="scope">
                        <span v-if="scope.row.accept===0">

                        <el-button @click="acceptStu(scope.row,1)" type="primary" style="padding: 3px"
                                   size="mini">同意</el-button>
                        <el-button @click="acceptStu(scope.row,2)" style="padding: 3px" size="mini">拒绝</el-button>
                        <el-button @click="deleteAccept(scope.row)" style="padding: 3px" size="mini" type="danger">删除
                        </el-button>
                        </span>
                        <span v-else>
                           <el-button style="padding: 3px" size="mini"
                                      @click="showEditAcceptView(scope.row)">修改申请状态</el-button>
                        </span>

                    </template>
                </el-table-column>
            </el-table>
            <div style="display: flex;justify-content: flex-end">
                <el-pagination
                        background
                        @current-change="currentChange"
                        @size-change="sizeChange"
                        layout="sizes, prev, pager, next, jumper, ->, total, slot"
                        :total="total">
                </el-pagination>
            </div>
        </div>
        <el-dialog
                :title="title"
                :visible.sync="dialogVisible"
                width="24%">
            <div>
                <el-form :model="accept" :rules="rules" ref="acceptForm">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="请修改:">

                                <el-radio v-model="accept.accept" :label="1">同意</el-radio>
                                <el-radio v-model="accept.accept" :label="2">拒绝</el-radio>

                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doAccept">确 定</el-button>
  </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AcceptInfo",
        data() {
            return {
                searchValue: {
                    carbrand: null,
                    carmaster: null,
                    name: null
                },
                title: '',
                importDataBtnIcon: 'el-icon-upload2',
                importDataDisabled: false,
                showAdvanceSearchView: false,
                allDeps: [],
                accepts: [],
                loading: false,
                popVisible: false,
                popVisible2: false,
                dialogVisible: false,
                total: 0,
                page: 1,
                keyword: '',
                size: 10,
                nations: [],
                joblevels: [],
                politicsstatus: [],
                positions: [],
                tiptopDegrees: ['本科', '大专', '硕士', '博士', '高中', '初中', '小学', '其他'],
                options: [{
                    value: 1,
                    label: '同意'
                }, {
                    value: '拒绝',
                    label: '双皮奶'
                }, {
                    value: '选项3',
                    label: '蚵仔煎'
                }, {
                    value: '选项4',
                    label: '龙须面'
                }, {
                    value: '选项5',
                    label: '北京烤鸭'
                }],
                inputDepName: '所属部门',
                accept: {
                    id: "",
                    carbrand: "宝马",
                    carmaster: "李四",
                    accept: 0,
                    price: 0.00,
                },
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                rules: {
                    brand: [{required: true, message: '请输入车辆品牌', trigger: 'blur'}],
                    model: [{required: true, message: '请输入车辆型号', trigger: 'blur'}],
                    telphone: [{required: true, message: '请输入电话号码', trigger: 'blur'}],
                    address: [{required: true, message: '请输入车主联系地址', trigger: 'blur'}],
                    carmaster: [{required: true, message: '请输入车主姓名', trigger: 'blur'}],
                    price: [{required: true, message: '请输入车辆价值', trigger: 'blur'}],
                    enginenum: [{required: true, message: '请输入车架号', trigger: 'blur'}],
                }
            }
        },
        mounted() {
            this.initAccepts();
            // this.initData();
            // this.initPositions();
        },
        methods: {
            acceptStu(data, type) {
                this.$confirm('此操作将修改【' + data.id + '】的状态, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.putRequest("/accept/basic/" + data.id + "/" + type).then(resp => {
                        if (resp) {
                            this.initAccepts();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            searvhViewHandleNodeClick(data) {
                this.inputDepName = data.name;
                this.searchValue.departmentId = data.id;
                this.popVisible2 = !this.popVisible2
            },
            onError(err, file, fileList) {
                this.importDataBtnText = '导入数据';
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
            },
            onSuccess(response, file, fileList) {
                this.importDataBtnText = '导入数据';
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
                this.initEmps();
            },
            beforeUpload() {
                this.importDataBtnText = '正在导入';
                this.importDataBtnIcon = 'el-icon-loading';
                this.importDataDisabled = true;
            },
            exportData() {
                window.open('/employee/basic/export', '_parent');
            },
            emptyCar() {
                this.accept = {
                    id: "",
                    brand: "",
                    model: "",
                    address: "",
                    telphone: null,
                    carmaster: "",
                    price: 0.00,
                    enginenum: "",
                }
            },
            showEditAcceptView(data) {
                // this.initPositions();
                this.title = '修改状态';
                this.accept = data;
                this.dialogVisible = true;
            },
            deleteCar(data) {
                this.$confirm('此操作将永久删除【' + data.id + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/car/basic/" + data.id).then(resp => {
                        if (resp) {
                            this.initAccepts();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            doAccept() {
                if (this.accept.id) {
                    this.$refs['acceptForm'].validate(valid => {

                        this.putRequest("/accept/basic/" + this.accept.id+"/"+this.accept.accept).then(resp => {
                            if (resp) {
                                this.dialogVisible = false;
                                this.initAccepts();
                            }
                        })

                    });
                } else {
                    this.$refs['acceptForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/accept/basic/", this.accept).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initAccepts();
                                }
                            })
                        }
                    });
                }
            },
            handleNodeClick(data) {
                this.inputDepName = data.name;
                this.emp.departmentId = data.id;
                this.popVisible = !this.popVisible
            },
            showDepView() {
                this.popVisible = !this.popVisible
            },
            showDepView2() {
                this.popVisible2 = !this.popVisible2
            },
            initPositions() {
                this.getRequest('/employee/basic/positions').then(resp => {
                    if (resp) {
                        this.positions = resp;
                    }
                })
            },
            getMaxWordID() {
                this.getRequest("/employee/basic/maxWorkID").then(resp => {
                    if (resp) {
                        this.emp.workID = resp.obj;
                    }
                })
            },
            initData() {
                if (!window.sessionStorage.getItem("nations")) {
                    this.getRequest('/car/basic/nations').then(resp => {
                        if (resp) {
                            this.nations = resp;
                            window.sessionStorage.setItem("nations", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.nations = JSON.parse(window.sessionStorage.getItem("nations"));
                }
                if (!window.sessionStorage.getItem("joblevels")) {
                    this.getRequest('/employee/basic/joblevels').then(resp => {
                        if (resp) {
                            this.joblevels = resp;
                            window.sessionStorage.setItem("joblevels", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.joblevels = JSON.parse(window.sessionStorage.getItem("joblevels"));
                }
                if (!window.sessionStorage.getItem("politicsstatus")) {
                    this.getRequest('/employee/basic/politicsstatus').then(resp => {
                        if (resp) {
                            this.politicsstatus = resp;
                            window.sessionStorage.setItem("politicsstatus", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.politicsstatus = JSON.parse(window.sessionStorage.getItem("politicsstatus"));
                }
                if (!window.sessionStorage.getItem("deps")) {
                    this.getRequest('/employee/basic/deps').then(resp => {
                        if (resp) {
                            this.allDeps = resp;
                            window.sessionStorage.setItem("deps", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.allDeps = JSON.parse(window.sessionStorage.getItem("deps"));
                }
            },
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initEmps();
            },
            currentChange(currentPage) {
                this.page = currentPage;
                this.initEmps();
            },
            showAddCarView() {
                this.emptyCar();
                this.title = '添加车辆';
                this.getMaxWordID();
                this.dialogVisible = true;
            },
            initAccepts(type) {
                this.loading = true;
                let url = '/accept/basic/?page=' + this.page + '&size=' + this.size;
                if (type && type === 'advanced') {
                    if (this.searchValue.name) {
                        url += '&name=' + this.searchValue.brand;
                    }
                    if (this.searchValue.carbrand) {
                        url += '&carbrand=' + this.searchValue.carbrand;
                    }
                    if (this.searchValue.carmaster) {
                        url += '&carmaster=' + this.searchValue.carmaster;
                    }
                } else {
                    url += "&carmaster=" + this.keyword;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.accepts = resp.data;
                        this.total = resp.total;
                    }
                });
            }
        }
    }
</script>

<style>
    /* 可以设置不同的进入和离开动画 */
    /* 设置持续时间和动画函数 */
    .slide-fade-enter-active {
        transition: all .8s ease;
    }

    .slide-fade-leave-active {
        transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
    }

    .slide-fade-enter, .slide-fade-leave-to
        /* .slide-fade-leave-active for below version 2.1.8 */
    {
        transform: translateX(10px);
        opacity: 0;
    }
</style>