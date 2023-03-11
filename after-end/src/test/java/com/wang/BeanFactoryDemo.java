package com.wang;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-10-22:25
 * @description
 */
public class BeanFactoryDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        // 设置上下文
        beanFactory.newFactory("这是上下文");
        beanFactory.print();
    }

}



interface BeanFactoryI {
    void newFactory(String context);
}

class BeanFactory implements BeanFactoryI {

    private String context;

    public void print() {
        System.out.println("上下文是：" + context);
    }

    @Override
    public void newFactory(String context) {
        // 获得上下文
        this.context = context;
    }
}







