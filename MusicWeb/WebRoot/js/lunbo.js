window.onload = function(){ 
				/*先获取页面容器，通过id获取container*/
				var container = document.getElementById("container");
				/*获取图片列表*/
				var list = document.getElementById("list");
				/*先用id获取下部五个按钮的div，然后再用标签（.getElementByTagName）来获取buttons盒子中的span*/
				//buttons相当于一个数组，第一个小圆点的下标为0
				var buttons = document.getElementById("buttons").getElementsByTagName("span");
				/*获取箭头*/
				//左箭头
				var prev = document.getElementById("prev");
				//右箭头
				var next = document.getElementById("next");
				//存放当前小圆点的位次号
				var index = 1;
				//用于存放动画函数是否在运行
				var animated = false;
				var timer;//自动轮播定时器参数
				//根据图片亮起小圆点的函数
				function showButton(){
					//当离开此张图片时，下面的圆点变灰色
					//用for循环找到buttons
					for (var i = 0; i < buttons.length; i++) {
						if (buttons[i].className == 'on') {
							buttons[i].className = '';
							break;
						}
					}
					buttons[index - 1].className = 'on';//设置class属性为on 上面设置的on类为橘色
				}	
				
				/*添加事件绑定*/
				//右箭头事件绑定
				function animate(offset){
					//当动画运行的时候，改变animated的值
					animated = true;
					//点击事件封装的函数
					var newLeft = parseInt(list.style.left) + offset;
					var time = 500; //位移总时间
					var interval = 10; //位移间隔时间
					var speed = offset/(time/interval);//求出每次的位移量
					function go(){
						//前一种情况：当speed小于0时，（图片向左移动的时候），并且现在的值大于目标值，目标值就是newLeft
						if (speed < 0 && parseInt(list.style.left) > newLeft || speed > 0 && parseInt(list.style.left) < newLeft) {
							list.style.left = parseInt(list.style.left) + speed + 'px';
							//调用go()函数
							//如果不满足目标值，继续调用go()函数（因为我们的位移间隔时间是10毫秒，所以需要定时器，在10ms后再次调用go()函数）
							//定时器
							setTimeout(go,interval);
						}else{
							animated = false;
							list.style.left = newLeft + "px";
					
					//判断一下，看left是否大于-1000或者小于-5000 然后进行跳转
					if (newLeft > -1000) {
						list.style.left = -5000 + 'px';
					} 
					if (newLeft < -5000) {
						list.style.left = -1000 + 'px';
					}
						}
					}
					//还得调用一下go() 函数
					go();
				}
				
				//自动轮播相关函数
				function play(){
					timer = setInterval(function(){
						next.onclick();
					},3000);//每隔三秒调用一次next.onclick();// 相当于每隔三秒点一下向右的按钮
				}
				function stop(){
					clearInterval(timer);
				}
				
				next.onclick = function(){
					//点击向右的按钮时，index加1
					if (index == 5) {
						index = 1;
					}else{
						index += 1;
					}
					//调用showButton() 函数
					showButton();
					if (!animated) {
						animate(-1000);
					}
				}
				//左箭头事件绑定
				prev.onclick = function(){
					//点击向左的按钮时，index减1
					if (index == 1) {
						index = 5;
					}else{
						index -= 1;
					}
					showButton();
					if (!animated) {
						//调用animate()函数，进行传值
						animate(1000);
					}
				}
				
				//用for循环为小圆点按钮加上点击事件
				for (var i = 0; i < buttons.length; i++) {
					buttons[i].onclick = function(){
						//代码优化，当点击当前图片的按钮时，什么也不做
						if(this.className == 'on'){
							return;
						}
						var myIndex = parseInt(this.getAttribute('index'));
						var offset = -1000 * (myIndex - index);
						//然后把偏移量传进animate() 函数
						if (!animated) {
						//调用animate()函数，进行传值
						animate(offset);
					}
						index = myIndex;
						showButton();
					}
				}
				//当鼠标放上去的时候触发事件停止自动轮播 stop
				container.onmouseover = stop;
				//当鼠标移开，出发时间开始自动轮播 play
				container.onmouseout = play;
				//一开始的状态是自动播放的
				play();
				
			}
			