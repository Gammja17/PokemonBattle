package gameplay;

public enum Type {
	노말, 물, 불, 풀;
	
	 public double effective(Type target) {
	        // 공격자: this, 방어자: target
	        if (this == 불) {
	            if (target == 풀) return 2.0;
	            if (target == 물) return 0.5;
	        } else if (this == 물) {
	            if (target == 불) return 2.0;
	            if (target == 풀) return 0.5;
	        } else if (this == 풀) {
	            if (target == 물) return 2.0;
	            if (target == 불) return 0.5;
	        }

	        return 1.0;
	    }
	
}

